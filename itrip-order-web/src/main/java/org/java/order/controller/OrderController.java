package org.java.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.java.common.pojo.ItripResult;
import org.java.common.util.CookieUtils;
import org.java.common.util.JsonUtils;
import org.java.order.pojo.OrderInfo;
import org.java.order.service.OrderInfoService;
import org.java.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {
	@Value("${ITRIP_CART}")
	private String ITRIP_CART;
	@Autowired
	private OrderInfoService orderInfoService;
	@RequestMapping("/order/order-cart")
	public String orderCart(Model model,HttpServletRequest request){
		//--------------------------遍历购物车，将购物车中的信息组装成一个OrderItem的一个集合--------------------------
		String json = CookieUtils.getCookieValue(request,ITRIP_CART,true);
		List<Item> list = new ArrayList<Item>();
		//如果取到了
		if(StringUtils.isNotBlank(json)){
			//转换成集合
			list = JsonUtils.jsonToList(json,Item.class);
		}
		/*//遍历购物车，将购物车中的商品放入到订单详情（因为订单详情中的商品就是购物车中要购买的商品）
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		for (Item item : list) {
			OrderItem orderItem = new OrderItem();
			orderItem.setId(IDUtils.genItemId()+"");//此处的详情id可以使用自增
			orderItem.setItemid(item.getId().toString());
			orderItem.setNum(item.getNum());
			orderItem.setTitle(item.getTitle());
			orderItem.setPrice(item.getPrice());
			//总金额
			orderItem.setTotalfee(item.getPrice().multiply(BigDecimal.valueOf(item.getNum())));
			orderItem.setPicpath(item.getImage());//图片
			//添加到集合中
			orderItems.add(orderItem);
		}
		model.addAttribute("cartList",orderItems);*/
		model.addAttribute("cartList",list);
		return "order-cart";
	}
	
	@RequestMapping("/order/create")
	public String createOrder(OrderInfo orderInfo,Model model,String cardType){
		//执行创建订单
		ItripResult result = orderInfoService.createOrder(orderInfo);
		if(orderInfo.getPaymenttype()==1){
			//表示货到付款，那么就可以直接跳转到成功页面
			model.addAttribute("orderId",result.getData());    //订单号
			model.addAttribute("payment",orderInfo.getPayment());  //总金额
			return "success";
		}else if(orderInfo.getPaymenttype()==2){
			//应该跳转到微信支付页面
			return "redirect:http://localhost:8088/pay/wx?orderId="+result.getData();
		}else{
			//跳转到网银支付界面
			//将易宝支付所需要的参数进行封装（各位自行封装）
			//转发到确认支付的页面
			
			return "redirect:http://localhost:8087/pay/yibao?cardType="+cardType+"&orderId="+result.getData();
		}
	}
	
	@RequestMapping("/order/success")
	public String paySuccess(String r6_Order,String r3_Amt,Model model){
		model.addAttribute("orderId",r6_Order);    //订单号
		model.addAttribute("payment",r3_Amt);  //总金额
		return "success";
	}
	
}
