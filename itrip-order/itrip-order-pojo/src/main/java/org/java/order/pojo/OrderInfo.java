package org.java.order.pojo;

import java.io.Serializable;
import java.util.List;

import org.java.pojo.Order;
import org.java.pojo.OrderItem;
import org.java.pojo.OrderShipping;

public class OrderInfo extends Order implements Serializable{
	private List<OrderItem> orderItems;
	private OrderShipping orderShipping;
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public OrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(OrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}
	
}
