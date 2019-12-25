package org.java.common.pojo;

import java.io.Serializable;

public class Ad2 implements Serializable{
	private String alt;//鼠标悬浮提示
	private String href;//点击后的秒杀地址
	private Integer index;//如果0表示第一张显示的秒杀对象
	private String src;//秒杀商品的图片地址
	private String ext;
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	
}
