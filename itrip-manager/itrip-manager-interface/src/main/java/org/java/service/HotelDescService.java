package org.java.service;

import org.java.pojo.ItemDesc;

public interface HotelDescService {
	/**
	 * 根据ItemId获取到详情对象
	 * @param id
	 * @return
	 */
	ItemDesc getDescByItemId(Long id);
}
