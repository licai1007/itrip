package org.java.order.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.java.common.pojo.ItripResult;
import org.java.common.util.IDUtils;
import org.java.mapper.OrderItemMapper;
import org.java.mapper.OrderMapper;
import org.java.mapper.OrderShippingMapper;
import org.java.order.pojo.OrderInfo;
import org.java.order.service.OrderInfoService;
import org.java.pojo.Order;
import org.java.pojo.OrderItem;
import org.java.pojo.OrderShipping;
import org.java.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class OrderInfoServiceImpl implements OrderInfoService{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderItemMapper orderItemMapper;
	@Autowired
	private OrderShippingMapper orderShippingMapper;
	@Autowired
	private JedisClient jedisClient;
	@Value("${ORDER_GEN_ID}")
	private String ORDER_GEN_ID;
	@Override
	public ItripResult createOrder(OrderInfo orderInfo) {
		//生成订单号
		String orderId = "";
		String strOrderId = jedisClient.get(ORDER_GEN_ID);
		if(StringUtils.isBlank(strOrderId)){
			//表示是系统第一次执行创建订单号
			//--生成一个当前时间的时间戳，然后将这个时间戳放入到redis中
			String systemTime = System.currentTimeMillis() + "";
			jedisClient.set(ORDER_GEN_ID,systemTime);
		}
		//订单号
		jedisClient.incr(ORDER_GEN_ID);//先自增
		//取出
		orderId = jedisClient.get(ORDER_GEN_ID);
		//------------------------------------------------------------------
		Order order = new Order();
		order.setOrderid(orderId);
		order.setPayment(orderInfo.getPayment());
		order.setPaymenttype(orderInfo.getPaymenttype());
		order.setPostfee("0");  //默认是包邮
		//状态：1,未付款；2,已付款；3,未发货；4,已发货；5,交易成功；6,交易关闭
		order.setStatus(1);   //刚下的订单默认是未付款
		order.setCreatetime(new Date());
		order.setUpdatetime(new Date());
		order.setUserid(orderInfo.getUserid());
		//持久化到数据库中
		orderMapper.insertSelective(order);
		//---------------------------------------------------------------------------
		for (OrderItem orderItem:orderInfo.getOrderItems()) {
			orderItem.setId(IDUtils.genItemId()+"");
			orderItem.setOrderid(orderId);
			//持久化到数据库中
			orderItemMapper.insertSelective(orderItem);
		}
		//---------------------------------------------------------------------------
		OrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderid(orderId);
		orderShipping.setCreated(new Date());
		orderShipping.setUpdated(new Date());
		orderShippingMapper.insertSelective(orderShipping);
		return ItripResult.ok(orderId);
	}

}
