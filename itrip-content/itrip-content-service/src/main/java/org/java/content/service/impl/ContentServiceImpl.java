package org.java.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.java.common.pojo.Ad;
import org.java.common.pojo.EasyUIDataGrideResult;
import org.java.common.pojo.ItripResult;
import org.java.common.util.JsonUtils;
import org.java.content.service.ContentService;
import org.java.mapper.ContentMapper;
import org.java.pojo.Content;
import org.java.pojo.ContentCriteria;
import org.java.pojo.ContentCriteria.Criteria;
import org.java.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ContentServiceImpl implements ContentService{
	@Autowired
	private ContentMapper contentMapper;
	@Value("${AD1_HEIGHT}")
	private String AD1_HEIGHT;
	@Value("${AD1_WIDTH}")
	private String AD1_WIDTH;
	@Value("${AD1_HEIGHTB}")
	private String AD1_HEIGHTB;
	@Value("${AD1_WIDTHB}")
	private String AD1_WIDTHB;
	@Value("${REDIS_AD}")
	private String REDIS_AD;
	@Autowired
	private JedisClient jedisClient;
	@Override
	public EasyUIDataGrideResult getContents(int categoryid,int pageNow,int pageSize) {
		//开始分页
		PageHelper.startPage(pageNow,pageSize);
		ContentCriteria example = new ContentCriteria();
		Criteria criteria = example.createCriteria();
		//添加条件
		criteria.andCategoryidEqualTo(categoryid);
		//执行查询
		List<Content> list = contentMapper.selectByExample(example);
		//转换成pageInfo对象
		PageInfo<Content> pageInfo = new PageInfo<Content>(list);
		EasyUIDataGrideResult result = new EasyUIDataGrideResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}
	@Override
	public ItripResult deleteContents(int[] ids) {
		try {
			for (int id : ids) {
				contentMapper.deleteByPrimaryKey(id);
			}
			//从redis中删除key
			jedisClient.hdel(REDIS_AD,"ad1");
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"删除内容失败");
		}
	}
	@Override
	public ItripResult saveContent(Content content) {
		try {
			//补全
			content.setCreated(new Date());
			content.setUpdated(new Date());
			contentMapper.insert(content);
			//从redis中删除key
			jedisClient.hdel(REDIS_AD,"ad1");
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"添加内容失败");
		}
	}
	@Override
	public List<Ad> getAd1() {
		//-----------查询前：先判断redis中是否有，如果有那么直接从redis中取出，就不需要查询数据库了-----------
		if(StringUtils.isNotBlank(jedisClient.hget(REDIS_AD,"ad1"))){
			//如果有，那么取出
			String ad1String = jedisClient.hget(REDIS_AD,"ad1");
			//将json格式的字符串转换成list集合
			List<Ad> adlist = JsonUtils.jsonToList(ad1String,Ad.class);
			return adlist;
		}
		ContentCriteria example = new ContentCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryidEqualTo(89);
		//查询大广告
		List<Content> list = contentMapper.selectByExample(example);
		System.out.println("大广告被查询了");
		List<Ad> adList = new ArrayList<Ad>();
		for (Content content : list) {
			Ad ad = new Ad();
			ad.setHeight(AD1_HEIGHT);
			ad.setWidth(AD1_WIDTH);
			ad.setAlt(content.getTitledesc());
			ad.setSrc(content.getPic());//图片的地址
			ad.setHref(content.getUrl());
			
			ad.setSrcB(content.getPic2());
			ad.setHeightB(AD1_HEIGHTB);
			ad.setWidthB(AD1_WIDTHB);
			//添加到集合中
			adList.add(ad);
		}
		//-----------如果程序能执行到这一步，那么表示redis中是没有的，那么查询后，将集合放入到redis中------------
		//广告实际的开发是更新不频繁的，因此我们只需要在添加或者修改后重新更新redis中的广告就行，不需要单独设置存在的时间
		jedisClient.hset(REDIS_AD,"ad1",JsonUtils.objectToJson(adList));
		return adList;
	}
	@Override
	public ItripResult updateContent(Content content) {
		try {
			//补全
			content.setUpdated(new Date());
			//修改
			contentMapper.updateByPrimaryKeySelective(content);
			//从redis中删除key
			jedisClient.hdel(REDIS_AD,"ad1");
			return ItripResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ItripResult.build(500,"修改内容失败");
		}
	}

}
