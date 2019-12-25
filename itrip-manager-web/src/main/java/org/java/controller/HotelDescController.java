package org.java.controller;

import org.java.common.pojo.ItripResult;
import org.java.pojo.ItemDesc;
import org.java.service.HotelDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HotelDescController {
	@Autowired
	private HotelDescService hotelDescService;
	@RequestMapping(value="/rest/item/query/item/desc/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ItripResult queryDesc(@PathVariable("id") long id){
		ItemDesc itemDesc = hotelDescService.getDescByItemId(id);
		return ItripResult.ok(itemDesc);
	}
}
