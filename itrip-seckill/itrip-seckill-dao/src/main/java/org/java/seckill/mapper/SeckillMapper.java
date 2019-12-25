package org.java.seckill.mapper;

import java.util.List;
import java.util.Map;

import org.java.seckill.pojo.Seckill;

public interface SeckillMapper {
	/**
	 * 查询所有的秒杀商品
	 */
	List<Seckill> getAllSeckill();
	
	/**
	 * 根据id获取对象
	 */
	Seckill getById(Long seckillId);
	
	/**
	 * 减少库存
	 */
	Integer reduceCount(Map<String,Object> map);
}