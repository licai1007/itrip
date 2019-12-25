package org.java.seckill.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.java.common.pojo.Ad2;
import org.java.common.pojo.ItripResult;
import org.java.common.util.JsonUtils;
import org.java.redis.JedisClient;
import org.java.seckill.dto.Exposer;
import org.java.seckill.exception.RepeatSeckillException;
import org.java.seckill.exception.SeckillClosedException;
import org.java.seckill.exception.SeckillException;
import org.java.seckill.mapper.SeckillMapper;
import org.java.seckill.mapper.SuccessKilledMapper;
import org.java.seckill.pojo.Seckill;
import org.java.seckill.pojo.SuccessKilled;
import org.java.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class SeckillServiceImpl implements SeckillService{
	@Autowired
	private SeckillMapper seckillMapper;
	@Autowired
	private SuccessKilledMapper successKilledMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${SECKILL_REDIS}")
	private String SECKILL_REDIS;
	@Value("${SECKILL_EXIST_TIME}")
	private int SECKILL_EXIST_TIME;
	@Value("${MD5_SALT}")
	private String MD5_SALT;
	@Override
	public List<Ad2> getSeckill() {
		List<Seckill> seckillList = seckillMapper.getAllSeckill();
		List<Ad2> list = new ArrayList<Ad2>();
		for (int i=0;i<seckillList.size();i++) {
			//取出秒杀的商品
			Seckill seckill = seckillList.get(i);
			Ad2 ad2 = new Ad2();
			ad2.setAlt("");
			ad2.setExt("");
			ad2.setHref("http://localhost:8089/seckill/"+seckill.getSeckillId());
			ad2.setIndex(i);
			if(StringUtils.isNotBlank(seckill.getImage())){
				String[] images = seckill.getImage().split(",");
				ad2.setSrc(images[0]);//就拿第一张做展示
			}
			list.add(ad2);
		}
		return list;
	}
	@Override
	public Seckill getById(long seckillId) {
		//首先到redis中进行查询
		String json = jedisClient.get(SECKILL_REDIS + seckillId);
		Seckill seckill = null;
		//判断
		if(StringUtils.isNotBlank(json)){
			//将json格式的字符串转换成一个对象
			seckill = JsonUtils.jsonToPojo(json,Seckill.class);
			return seckill;
		}
		//如果redis中没有，那么就从数据库中查询
		seckill = seckillMapper.getById(seckillId);
		//放入到redis中
		jedisClient.set(SECKILL_REDIS + seckillId,JsonUtils.objectToJson(seckill));
		//设置存在时间为一天
		jedisClient.expire(SECKILL_REDIS + seckillId,SECKILL_EXIST_TIME);
		return seckill;
	}
	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		//1.根据编号查询对象
		Seckill seckill = this.getById(seckillId);
		//2.根据对象获取到秒杀开始和结束的时间
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		//3.获取系统服务器的时间
		Date nowTime = new Date();
		//4.将时间转换成long类型的值直接比较大小
		if(nowTime.getTime()>endTime.getTime() || nowTime.getTime()<startTime.getTime()){
			//秒杀活动已经结束或者是没有开始秒杀
			return new Exposer(false,nowTime.getTime(),startTime.getTime(),endTime.getTime());
		}
		//5.在秒杀时间内，那么先经过md5加密暴露秒杀接口
		String md5 = md5(seckillId,MD5_SALT);
		return new Exposer(true,md5,seckillId);
	}
	public String md5(long str,String salt){
		String md = str + salt;
		return DigestUtils.md5DigestAsHex(md.getBytes());
	}
	@Override
	public ItripResult executeSeckill(Long seckillId, String userPhone,
			String md5) throws SeckillException, RepeatSeckillException,
			SeckillClosedException {
		if(md5 == null || !md5.equals(md5(seckillId,MD5_SALT))){
			//用户不是用系统暴露的秒杀接口进行秒杀
			throw new SeckillException("非法秒杀");
		}
		//--------------------------减少库存，并且在购买记录表中添加一行数据--------------------------
		//购买记录中添加一行数据
		SuccessKilled successKilled = new SuccessKilled();
		successKilled.setSeckillId(seckillId);
		successKilled.setUserPhone(userPhone);//应该是从cookie中取出
		int insertCount = successKilledMapper.insertSuccessKilled(successKilled);
		if(insertCount == 0){
			//表示重复秒杀
			throw new RepeatSeckillException("重复秒杀");
		}
		//减少库存
		Date now = new Date();
		//根据id查询对象(此处涉及到了多线程的处理问题)
		Seckill seckill = this.getById(seckillId);
		if(now.getTime()>seckill.getEndTime().getTime() || now.getTime()<seckill.getStartTime().getTime()){
			//不在秒杀时间内
			throw new SeckillClosedException("不在秒杀时间内");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("seckill_id",seckillId);
		map.put("nowtime",new Date());
		seckillMapper.reduceCount(map);
		return ItripResult.build(200,"秒杀成功",seckillId);
	}

}
