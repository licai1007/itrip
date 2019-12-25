package org.java.protal.controller;

import java.util.List;

import org.java.common.pojo.Ad;
import org.java.common.pojo.Ad2;
import org.java.content.service.ContentService;
import org.java.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;

@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;
	@Autowired
	private SeckillService seckillService;
	@RequestMapping("/index")
	public String showIndex(Model model){
		//-----------------查询大广告----------------------
		List<Ad> ad1list = contentService.getAd1();
		//将集合转换成json格式的字符串转发到首页
		String ad1json = JSON.toJSONString(ad1list);
		model.addAttribute("ad1",ad1json);
		//-----------------查询秒杀商品----------------------
		List<Ad2> list = seckillService.getSeckill();
		String seckill = JSON.toJSONString(list);
		model.addAttribute("ad2",seckill);
		return "index";
	}
}
