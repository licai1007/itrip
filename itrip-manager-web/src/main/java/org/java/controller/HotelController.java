package org.java.controller;

import org.java.common.pojo.EasyUIDataGrideResult;
import org.java.common.pojo.ItripResult;
import org.java.pojo.Item;
import org.java.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HotelController {
	@Autowired
	private HotelService hotelService;
	@RequestMapping("/getById")
	@ResponseBody
	public Item getById(Long id){
		return hotelService.getById(id);
	}
	@RequestMapping("/{pages}")
	public String test(@PathVariable String pages){
		return pages;
	}
	/**
	 * 在EasyUI中，进行翻页的时候传入的两个参数叫page（当前的页码），rows（每页显示的信息条数）
	 * @return
	 */
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGrideResult showList(int page,int rows){
		return hotelService.getHotelList(page,rows);
	}
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public ItripResult saveHotel(Item item){
		//添加到数据库中
		return hotelService.saveHotel(item);
	}
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public ItripResult itemDelete(long[] ids){
		return hotelService.deleteHotel(ids);
	}
	//商品下架
	@RequestMapping(value="/rest/item/instock",method=RequestMethod.POST)
	@ResponseBody
	public ItripResult instock(long[] ids){
		return hotelService.xiaJia(ids);
	}
	//商品上架
	@RequestMapping(value="/rest/item/reshelf",method=RequestMethod.POST)
	@ResponseBody
	public ItripResult reshelf(long[] ids){
		return hotelService.shangJia(ids);
	}
	//显示编辑商品页面
	@RequestMapping("/rest/page/item-edit")
	public String showEdit(){
		return "item-edit";
	}
	//修改商品
	@RequestMapping(value="/rest/item/update",method=RequestMethod.POST)
	@ResponseBody
	public ItripResult update(Item item){
		return hotelService.update(item);
	}
	
}
