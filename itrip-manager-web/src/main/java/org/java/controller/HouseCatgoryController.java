package org.java.controller;

import java.util.List;

import org.java.common.pojo.EasyUITreeNodeResult;
import org.java.common.pojo.ItripResult;
import org.java.pojo.ItemCat;
import org.java.service.HotelCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HouseCatgoryController {
	@Autowired
	private HotelCatService hotelCatService;
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNodeResult> showCatList(@RequestParam(name="id",defaultValue="0") int parentId){
		return hotelCatService.getTreeNodeByParentId(parentId);
	}
	@RequestMapping(value="/rest/item/query/itemcat/name/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ItripResult showCat(@PathVariable("id") int id){
		ItemCat itemCat = hotelCatService.getItemCatById(id);
		return ItripResult.ok(itemCat);
	}
}
