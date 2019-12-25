package org.java.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.java.common.util.CookieUtils;
import org.java.common.util.JsonUtils;
import org.java.pojo.User;
import org.java.redis.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * 拦截器：实现HandlerInterceptor接口
 * @author licai
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
	@Value("${ITRIP_COOKIE}")
	private String ITRIP_COOKIE;
	@Value("${USER_SESSION}")
	private String USER_SESSION;
	@Autowired
	private JedisClient jedisClient;
	//在Handler中的method执行之前执行（实际应用场景：登录验证）
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//获取到当前的url
		String url = request.getRequestURL().toString();
		//返回true表示放行，返回false表示拦截
		//1.从cookie中取出token
		String token = CookieUtils.getCookieValue(request,ITRIP_COOKIE);
		//取不到token（直接返回到登录页面）
		if(StringUtils.isBlank(token)){
			//返回到登录页面
			response.sendRedirect("http://localhost:8084/user/showLogin?redirectUrl="+url);
			return false;      //表示拦截
		}
		//2.到redis中去进行验证
		String json = jedisClient.get(USER_SESSION+token);
		//redis中没有表示已经过期
		if(StringUtils.isBlank(json)){
			//表示已经过期
			//返回到登录页面
			response.sendRedirect("http://localhost:8084/user/showLogin?redirectUrl="+url);
			return false;      //表示拦截
		}
		//将用户对象放入到request域中
		User user = JsonUtils.jsonToPojo(json,User.class);
		request.setAttribute("user",user);
		//如果有收件表，那么此处应该是根据用户id查询用户的收货地址列表
		//3.查询到了，放行
		return true;
	}
	//在Handler中的method执行过程中执行（实际应用场景：程序的共有模块）
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	//在Handler中的method执行之后执行（实际应用场景：日志，记录用户的行为）
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
