package org.java.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.java.common.pojo.ItripResult;
import org.java.common.util.CookieUtils;
import org.java.common.util.JsonUtils;
import org.java.pojo.User;
import org.java.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Value("${ITRIP_COOKIE}")
	private String ITRIP_COOKIE;
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public ItripResult checkData(@PathVariable(value="param") String param,
			@PathVariable(value="type") Integer type){
		return userService.checkData(param,type);
	}
	
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public ItripResult register(User user){
		return userService.register(user);
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public ItripResult login(String username,String password,
			HttpServletRequest request,HttpServletResponse response){
		ItripResult result = userService.login(username,password);
		//判断是否登录成功
		if(result.getStatus() == 200){
			//表示成功，将token放入到cookie中
			CookieUtils.setCookie(request,response,ITRIP_COOKIE,result.getData().toString());
		}
		return result;
	}
	
	//第一种jsonp处理方案：要求spring的版本是4.1或以上
	/*@RequestMapping(value="/user/token/{token}",method=RequestMethod.GET)
	@ResponseBody
	public Object getUserByToken(@PathVariable("token") String token,String callback){
		ItripResult result = userService.getUserByToken(token);
		//处理json跨域请求
		if(StringUtils.isNotBlank(callback)){
			//请求参数中有callback，表示一个jsonp的请求（要求json跨域请求）
			MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
			//设置回调方法
			jacksonValue.setJsonpFunction(callback);
			return jacksonValue;
		}
		//非json跨域请求
		return result;
	}*/
	//对于spring是4.1以下的同志必须使用如下处理方案（强行将返回的对象转换成文本进行返回）
	@RequestMapping(value="/user/token/{token}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String getUserByToken(@PathVariable("token") String token,String callback){
		ItripResult result = userService.getUserByToken(token);
		//处理json跨域请求
		if(StringUtils.isNotBlank(callback)){
			//表示是jsonp的请求
			String str = callback + "(" + JsonUtils.objectToJson(result) + ");";
			return str;
		}
		//非json跨域请求
		return JsonUtils.objectToJson(result);
	}
	
	@RequestMapping(value="/user/logout/{token}",method=RequestMethod.GET)
	public String logout(@PathVariable("token") String token,
			HttpServletRequest request,HttpServletResponse response){
		//在浏览器中删除cookie
		CookieUtils.deleteCookie(request,response,ITRIP_COOKIE);
		return "redirect:http://localhost:8081";
	}
	
	@RequestMapping(value="/user/jihuo",method=RequestMethod.GET)
	public String jihuo(@RequestParam(name="code",required=false,defaultValue="") String code){
		//进行激活
		ItripResult result = userService.jihuo(code);
		if(result.getStatus()==400){
			//表示激活失败，应该跳转到一个失败页面
		}
		//激活成功
		return "redirect:/user/showLogin";
	}
	
	@RequestMapping(value="/user/sendCode",method=RequestMethod.POST)
	public void sendCode(@RequestParam(name="phone",required=true) String phone){
		userService.sendCode(phone);
	}

}
