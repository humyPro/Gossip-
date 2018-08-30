package com.gossip.sso.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gossip.pojo.User;
import com.gossip.sso.service.UserService;
import com.gossip.util.GetMapper;
import com.gossip.vo.SysResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author humy
 * @date 2018/8/5 - 0:04
 */
@Controller

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    @ResponseBody
    public int register(User user) {
       return userService.register(user);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name) {

        return "hello," + name;

    }



    @GetMapping("/user/check/{param}/{type}")
    @ResponseBody
    public JSONPObject check(@PathVariable String param, @PathVariable Integer type, String callback) {
        Boolean exists = userService.check(param, type);
        JSONPObject result = new JSONPObject(callback, SysResult.oK(exists));
        return result;
    }

    @PostMapping("/user/login")
    @ResponseBody
    public String login(User user){
    	System.out.println("con");
        String ticket=userService.login(user);
        return ticket;
    }
    @GetMapping("/user/query")
    @ResponseBody
    public User queryUserByTicket(@RequestParam("ticket") String ticket){
        User user=userService.queryUserByTicket(ticket);
        return user;
    }


    @PostMapping("/user/update")
    @ResponseBody
    public SysResult userUpdate(@RequestParam("ticket") String ticket,User user){
        SysResult result=userService.userUpdate(ticket,user);
        return result;
    }
    
	
	/*
	 * 后台登录
	 */
	@RequestMapping("/admin/login")
	@ResponseBody
	public SysResult login(String email,String password) throws Exception{
		SysResult result = userService.login(email,password);
		return result;
	}
	
	/*
	 * 检测email信息
	 */
	@RequestMapping("/admin/query/{ticket}")
	@ResponseBody
	public String query(String callback,@PathVariable String ticket) throws Exception{
		String emailJson = userService.getEmailJson(ticket);
		SysResult result = SysResult.oK(emailJson);
		String jsonData = GetMapper.getMapper().writeValueAsString(result);
		if(StringUtils.isNotEmpty(callback)){
			return callback + "(" + jsonData + ")";
		}else{
			return jsonData;
		}
	}

	/*
	 * 用户登出
	 */
	@RequestMapping("/admin/logout")
	public SysResult logout(@RequestParam("key") String key) throws Exception {
		SysResult result = userService.logout(key);
		return result;
	}
	
}
