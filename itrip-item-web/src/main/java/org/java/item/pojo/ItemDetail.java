package org.java.item.pojo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.java.pojo.Item;

public class ItemDetail extends Item implements Serializable{
	private String[] images;

	public String[] getImages() {
		String image = this.getImage();
		//判断是否有图片
		if(StringUtils.isBlank(image)){
			return null;
		}
		return image.split(",");
	}

	public ItemDetail() {
		super();
	}
	public ItemDetail(Item item){
		this.setId(item.getId());
		this.setTitle(item.getTitle());
		this.setSellpoint(item.getSellpoint());
		this.setPrice(item.getPrice());
		this.setNum(item.getNum());
		this.setBarcode(item.getBarcode());
		this.setImage(item.getImage());
		this.setCid(item.getCid());
	}
}
