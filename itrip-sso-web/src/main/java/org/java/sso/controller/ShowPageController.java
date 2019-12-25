package org.java.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShowPageController {
	@RequestMapping("/user/showRegister")
	public String register(){
		return "register";
	}
	@RequestMapping("/user/showLogin")
	public String login(@RequestParam("redirectUrl") String redirectUrl,Model model){
		model.addAttribute("redirect",redirectUrl);
		//转发到登录页面
		return "login";
	}
}
