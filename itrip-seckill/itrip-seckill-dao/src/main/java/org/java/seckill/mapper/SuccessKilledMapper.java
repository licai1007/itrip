package org.java.seckill.mapper;

import org.java.seckill.pojo.SuccessKilled;

public interface SuccessKilledMapper {
	/**
	 * 增加一条秒杀成功记录
	 * @param successKilled
	 * @return
	 */
	int insertSuccessKilled(SuccessKilled successKilled);
}
