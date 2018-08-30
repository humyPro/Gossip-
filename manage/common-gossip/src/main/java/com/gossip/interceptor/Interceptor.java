package com.gossip.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.pojo.User;
import com.gossip.service.HttpClientService;
import com.gossip.util.CookieUtils;
import com.gossip.vo.SysResult;


/*
 * 需要执行发帖,点赞,转发功能时,需拦截进行登录认证
 */
public class Interceptor implements HandlerInterceptor{

	@Autowired
	private HttpClientService client;
	public static final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 1.判断登录,通过cookie判断
		// 2.如果登录成功,ticket userJson都存在,将userId获取,放到request作用域返回true,传给controller
		// 3.不成功,表示没登录,转向登录,返回false
		
		
		return false; // 如果逻辑返回true表示request请求放行 false表示不放行
	}
	
}
