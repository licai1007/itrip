package org.java.seckill.controller;

import java.util.Date;

import org.java.common.pojo.ItripResult;
import org.java.seckill.dto.Exposer;
import org.java.seckill.exception.RepeatSeckillException;
import org.java.seckill.exception.SeckillClosedException;
import org.java.seckill.exception.SeckillException;
import org.java.seckill.pojo.Seckill;
import org.java.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SeckillController {
	@Autowired
	private SeckillService seckillService;
	
	@RequestMapping("/seckill/{seckillId}")
	public String seckilljsp(@PathVariable("seckillId") long seckillId,Model model){
		Seckill seckill = seckillService.getById(seckillId);
		//放入到模型中
		model.addAttribute("seckill",seckill);
		return "seckill";
	}
	
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public ItripResult timeNow(){
		//返回服务器的当前系统时间
		return ItripResult.ok(new Date().getTime());
	}
	
	@RequestMapping(value="/{seckillId}/exposer",method=RequestMethod.POST)
	@ResponseBody
	public Exposer exposer(@PathVariable("seckillId") long seckillId){
		return seckillService.exportSeckillUrl(seckillId);
	}
	
	//真实的执行秒杀
	@RequestMapping(value="/{seckillId}/{md5}/execution",method=RequestMethod.POST)
	@ResponseBody
	public ItripResult executeSeckill(@PathVariable("seckillId") long seckillId,
			@PathVariable("md5") String md5,
			@CookieValue(value="killPhone",required=false) String userPhone){
		try {
			ItripResult result = seckillService.executeSeckill(seckillId,userPhone,md5);
			return result;
		} catch (RepeatSeckillException e) {
			return ItripResult.build(400,"重复秒杀");
		} catch (SeckillClosedException e) {
			return ItripResult.build(400,"不在秒杀时间内");
		} catch (SeckillException e) {
			return ItripResult.build(400,"秒杀异常");
		} catch (Exception e) {
			return ItripResult.build(400,"秒杀未知异常");
		}
	}

}
