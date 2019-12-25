package org.java.search.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.java.search.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 统一异常处理类
 * @author licai
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver{
	//创建logger对象（日志）
	Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		//将异常写入到日志文件中
		logger.error("系统异常",ex.getMessage());
		//发邮件、发短信
		//Jmail：可以查找相关的资料
		//需要在购买短信。调用第三方接口即可。
		HttpClientUtil client = HttpClientUtil.getInstance();
		//UTF发送
		int result = client.sendMsgUtf8("caicaihao","d41d8cd98f00b204e980","我的系统出错【爱旅行网k8502】","18858155782");
		if(result>0){
			System.out.println("UTF8成功发送条数=="+result);
		}else{
			System.out.println(client.getErrorMsg(result));
		}
		//展示错误页面
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forward:/error.html");
		mv.addObject("message","你的电脑有问题");
		return mv;
	}

}
