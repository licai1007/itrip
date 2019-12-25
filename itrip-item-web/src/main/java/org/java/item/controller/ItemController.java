package org.java.item.controller;

import org.java.item.pojo.ItemDetail;
import org.java.pojo.Item;
import org.java.pojo.ItemDesc;
import org.java.service.HotelDescService;
import org.java.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {
	@Autowired
	private HotelService hotelService;
	@Autowired
	private HotelDescService hotelDescService;
	@RequestMapping("/item/{itemId}")
	public String getItemDetails(@PathVariable("itemId") long itemId,Model model){
		//根据商品id获取到对象
		Item item = hotelService.getById(itemId);
		//获取到详情
		ItemDesc itemDesc = hotelDescService.getDescByItemId(itemId);
		//将item对象转换成itemDetail对象
		ItemDetail itemDetail = new ItemDetail(item);
		model.addAttribute("item",itemDetail);
		model.addAttribute("itemDesc",itemDesc);
		return "item";
		//return "redirect:http://localhost:8888/"+itemId+".html";
	}
}
