package org.java.seckill.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀商品类
 * @author licai
 *
 */
public class Seckill implements Serializable{
	/** 秒杀商品编号. */
	private Long seckillId;
	/** 秒杀商品名称. */
	private String name;
	/** 卖点. */
	private String sellPoint;
	/** 库存. */
	private Integer number;
	/** 原价. */
	private double oldPirce;
	/** 秒杀价. */
	private double newPrice;
	/** 创建时间. */
	private Date createTime;
	/** 条形码. */
	private String barcode;
	/** 商品图片. */
	private String image;
	/** 分类id. */
	private Long cid;
	/** 商品状态，1-正常 2-下架 3-删除. */
	private Integer status;
	/** 秒杀开始时间. */
	private Date startTime;
	/** 秒杀结束时间. */
	private Date endTime;
	public Long getSeckillId() {
		return seckillId;
	}
	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public double getOldPirce() {
		return oldPirce;
	}
	public void setOldPirce(double oldPirce) {
		this.oldPirce = oldPirce;
	}
	public double getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(double newPrice) {
		this.newPrice = newPrice;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
