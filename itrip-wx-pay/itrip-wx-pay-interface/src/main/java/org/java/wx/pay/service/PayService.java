package org.java.wx.pay.service;

import org.java.common.pojo.ItripResult;

public interface PayService {
	/**
	 * 根据id修改状态
	 * @param oid
	 * @return
	 */
	ItripResult updateOrderState(String oid);
}
