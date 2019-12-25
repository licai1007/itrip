package org.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {
	@RequestMapping("/test1")
	public String test(){
		return "test";
	}
}
