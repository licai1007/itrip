package org.java.seckill.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 成功秒杀后的记录表
 * @author licai
 *
 */
public class SuccessKilled implements Serializable{
	/** 秒杀商品编号. */
	private Long seckillId;
	/** 用户电话. */
	private String userPhone;
	/** 状态标识：-1:无效 0:成功 1:已付款 2:已发货. */
	private Integer state;
	/** 秒杀成功时间. */
	private Date createTime;
	public Long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
