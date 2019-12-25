package org.java.seckill.dto;

import java.io.Serializable;

/**
 * 曝光器(用来曝光秒杀接口)
 * @author licai
 *
 */
public class Exposer implements Serializable{
	/** 是否暴露秒杀接口. */
	private boolean exposed;
	/** 对接口进行MD5加密. */
	private String md5;
	/** 参与秒杀的商品编号. */
	private long seckillId;
	/** 当前服务器时间. */
	private long now;
	/** 秒杀开始时间. */
	private long start;
	/** 秒杀结束时间. */
	private long end;
	
	public Exposer() {
		super();
	}
	
	public Exposer(boolean exposed, long now, long start, long end) {
		super();
		this.exposed = exposed;
		this.now = now;
		this.start = start;
		this.end = end;
	}
	
	public Exposer(boolean exposed, String md5, long seckillId) {
		super();
		this.exposed = exposed;
		this.md5 = md5;
		this.seckillId = seckillId;
	}

	public boolean isExposed() {
		return exposed;
	}
	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}
	public long getNow() {
		return now;
	}
	public void setNow(long now) {
		this.now = now;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(long end) {
		this.end = end;
	}
	
}
