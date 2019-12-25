package org.java.wx.pay.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.java.wx.pay.service.PayService;
import org.java.wx.pay.utils.PayConfig;
import org.java.wx.pay.utils.PayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wxpay.sdk.WXPay;

@Controller
public class WxPayController {
	@Autowired
	private PayService payService;
	@RequestMapping("/pay/wx")
	public String pay(Model model,HttpServletRequest request,String orderId){
		//订单编号
		String payid = orderId;
		String body = "爱旅行在线扫码支付";//商品描述
		String total_fee = "0.01";//实际要支付的金额(这是设置为每次支付1分钱)
		String userip = PayUtils.getRemoteAddr(request);//终端ip，也就是支付用户的ip地址
		//回调地址
		String callback = "http://localhost:8089/pay/wx/success";
		//生成一个code_url
		String code_url = PayUtils.pay(payid,userip,total_fee,body,callback);
		//放入到request域中
		model.addAttribute("code_url",code_url);
		model.addAttribute("oid",payid);
		model.addAttribute("totalprice",0.01);
		System.out.println("code_url:" + code_url);
		//进入到二维码生成页面
		return "saoma";
	}
	
	@RequestMapping("/wx/checkStatus")
	@ResponseBody
	public String checkStatus(String oid) throws Exception{
		String flag = "false";
		//如果支付成功那么返回true，否则返回false
		//添加订单号
		Map<String, String> data = new HashMap<String, String>();
		data.put("out_trade_no",oid);
		//商户基本信息配置
		PayConfig payConfig = new PayConfig();
		WXPay wxPay = new WXPay(payConfig);
		//查询订单
		Map<String, String> orderQuery = wxPay.orderQuery(data);
		//遍历查询结果
		Set<String> keySet = orderQuery.keySet();
		for (String key : keySet) {
			if("trade_state_desc".equals(key) && "支付成功".equals(orderQuery.get(key))){
				flag = "true";//表示支付成功
				//修改订单的状态
				payService.updateOrderState(oid);
			}
		}
		return flag;
	}
	
	@RequestMapping("/wx/paysuccess")
	public String success(){
		return "pay_success";
	}
	
}
