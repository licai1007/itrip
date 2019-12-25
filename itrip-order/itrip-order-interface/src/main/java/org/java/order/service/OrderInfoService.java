package org.java.order.service;

import org.java.common.pojo.ItripResult;
import org.java.order.pojo.OrderInfo;

public interface OrderInfoService {
	/**
	 * 创建订单
	 * @param orderInfo    订单对象
	 * @return
	 */
	ItripResult createOrder(OrderInfo orderInfo);
}
