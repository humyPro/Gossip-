package com.gossip.contorller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gossip.pojo.User;
import com.gossip.service.UserService;
import com.gossip.util.BrowseTool;
import com.gossip.util.CookieUtils;
import com.gossip.vo.SysResult;

@Controller
public class UserManageController {
	//后台管理登录
	@Autowired
	private UserService userService;
	@RequestMapping("/dologin")
	@ResponseBody
	public SysResult login(User user, HttpServletRequest request, HttpServletResponse response){
		// user传给service,调用http访问sso
		String ticket = userService.login_shiro(user,request);
//		String userAgent = request.getHeader("user-agent");
//		BrowseTool browseTool = new BrowseTool();
//		String browserType = browseTool.checkBrowse(userAgent);
//		System.out.println("浏览器类型"+browserType);
//		System.out.println("aaa"+ticket);
		// ticket是否为空; 只要是null或者""都是false
		if (StringUtils.isNotEmpty(ticket)) {
			// 表示登录成功,将ticket写进cookie
			// common中封装了cookie的各种方法
			// COOKIE中的key定义为JT_TICKET
			CookieUtils.setCookie(request, response, "USER_TICKET", ticket);
			return SysResult.build(200, "成功了");
		} else {// 登录失败,ticket是空
			return SysResult.build(201, "失败了");
		}
	}
	/*
	 * 登出请求
	 */
	@RequestMapping("/admin/logout")
	@ResponseBody
	public SysResult doLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysResult result = new SysResult();
		String key = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("USER_TICKET")) {
					key = cookie.getValue();
//				System.out.println("123:" + key);
				}
			}
			String ticket = userService.doLogout(key);
		}
		// user传给service,调用http访问sso
//		System.out.println("456:" + ticket);

		// 清空cookie
		// CookieUtils.setCookie(request, response, "JT_TICKET", ticket);
		CookieUtils.deleteCookie(request, response, "USER_TICKET");
		result.setStatus(200);
		result.setMsg("退出成功");
		return result;
	}
	
}
