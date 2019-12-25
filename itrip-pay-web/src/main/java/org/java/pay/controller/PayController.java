package org.java.pay.controller;

import org.java.pay.utils.PaymentUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayController {
	@RequestMapping("/pay/yibao")
	public String pay(String cardType,String orderId,Model model){
		String p0_Cmd = "Buy";//业务类型
		String p1_MerId = "10001126856";//商户编号(最终钱到账的账户id)，我们使用易宝支付官方提供的一个测试账号
		String p2_Order = orderId;//订单编号(如果你的订单号很简单，比如说1一旦有人在易宝支付上支付过该订单号后，将不能再重复支付)
		String p3_Amt = "0.01";//支付金额(按道理来说应该是实际的支付金额，但是这里为了方便测试，所以每次支付1分钱)
		String p4_Cur = "CNY";//交易币种
		String p5_Pid = "ceshi shangpin";//商品名称
		String p6_Pcat = "lv you";//商品种类
		String p7_Pdesc = "ceshi shangpin";//商品描述
		String p8_Url = "http://localhost:8086/order/success";//支付成功后易宝支付会向该地址发送两次成功通知
		String p9_SAF = "0";//送货地址
		String pa_MP = "";//商户扩展信息
		String pd_FrpId = cardType;//支付通道编码--实际上就是选择要支付的银行
		String pr_NeedResponse = "1";//应答机制
		//商户密钥
		String keyValue = "69c15252Av6q613Ii4w6u8k6xuw8vmIn6bFgyv769220IuYe9u37N4y7rI4PI";
		//通过一套加密算法生成一个hmac码（支付的安全就是通过该hmac码来进行保障的--下次讲解安全保障的原理）
		String hmac = PaymentUtil.buildHmac(p0_Cmd,p1_MerId,p2_Order,p3_Amt,p4_Cur,p5_Pid,p6_Pcat,p7_Pdesc,p8_Url,p9_SAF,pa_MP,pd_FrpId,pr_NeedResponse,keyValue);
		model.addAttribute("p0_Cmd",p0_Cmd);
		model.addAttribute("p1_MerId",p1_MerId);
		model.addAttribute("p2_Order",p2_Order);
		model.addAttribute("p3_Amt",p3_Amt);
		model.addAttribute("p4_Cur",p4_Cur);
		model.addAttribute("p5_Pid",p5_Pid);
		model.addAttribute("p6_Pcat",p6_Pcat);
		model.addAttribute("p7_Pdesc",p7_Pdesc);
		model.addAttribute("p8_Url",p8_Url);
		model.addAttribute("p9_SAF",p9_SAF);
		model.addAttribute("pa_MP",pa_MP);
		model.addAttribute("pd_FrpId",pd_FrpId);
		model.addAttribute("pr_NeedResponse",pr_NeedResponse);
		model.addAttribute("hmac",hmac);
		return "confirm";
	}
	
	/*//进入到回调地址后支付，支付成功后需要修改订单的状态为已经支付
	@RequestMapping("/pay/success")
	public String success(String r6_Order,String ro_BankOrderId){
		//需要根据订单编号修改订单的状态
		//修改成功后跳转到支付成功页面
		return "";
	}*/
}
