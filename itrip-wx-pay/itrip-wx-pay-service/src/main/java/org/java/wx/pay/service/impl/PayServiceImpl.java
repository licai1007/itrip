package org.java.wx.pay.service.impl;

import org.java.common.pojo.ItripResult;
import org.java.mapper.OrderMapper;
import org.java.pojo.Order;
import org.java.wx.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PayServiceImpl implements PayService{
	@Autowired
	private OrderMapper orderMapper;
	@Override
	public ItripResult updateOrderState(String oid) {
		Order record = new Order();
		record.setOrderid(oid);
		//状态：1,未付款；2,已付款
		record.setStatus(2);
		int result = orderMapper.updateByPrimaryKeySelective(record);
		if(result == 1){
			//修改成功一条数据
			return ItripResult.ok();
		}
		return ItripResult.build(500,"订单状态修改为支付失败");
	}

}
