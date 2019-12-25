package org.java.seckill.service;

import java.util.List;

import org.java.common.pojo.Ad2;
import org.java.common.pojo.ItripResult;
import org.java.seckill.dto.Exposer;
import org.java.seckill.exception.RepeatSeckillException;
import org.java.seckill.exception.SeckillClosedException;
import org.java.seckill.exception.SeckillException;
import org.java.seckill.pojo.Seckill;

public interface SeckillService {
	/**
	 * 查询所有的在架秒杀商品
	 * @return
	 */
	List<Ad2> getSeckill();
	/**
	 * 根据编号查询对象
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	/**
	 * 是否曝光秒杀接口
	 * @param seckillId
	 * @return
	 */
	Exposer exportSeckillUrl(long seckillId);
	/**
	 * 执行秒杀
	 * @param seckillId            秒杀商品编号
	 * @param userPhone            用户电话
	 * @param md5                  加密字符串(用来保证接口的安全)
	 * @return
	 * @throws SeckillException              秒杀执行异常
	 * @throws RepeatSeckillException        重复秒杀异常
	 * @throws SeckillClosedException        不在秒杀时间内异常
	 */
	ItripResult executeSeckill(Long seckillId,String userPhone,String md5)
		 throws SeckillException,RepeatSeckillException,SeckillClosedException;
}
