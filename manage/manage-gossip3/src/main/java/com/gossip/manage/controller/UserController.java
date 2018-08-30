package com.gossip.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gossip.manage.pojo.User;
import com.gossip.manage.service.UserService;
import com.gossip.vo.SysResult;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	
	//用户总数
	@RequestMapping("/manage/findAllUserCount")
	public SysResult findAllUser(){
		Long userCount = userService.findAllUserCount();
		return SysResult.oK(userCount);
	}
	
	
	//今日上线用户数
	@RequestMapping("/manage/today/online")
	public SysResult todayOnline(){
		Long onlineCount = userService.todayOnline();
		return SysResult.oK(onlineCount);
	}
	
	//今日注册用户数
	@RequestMapping("/manage/today/regist")
	public SysResult todayRegist(){
		Long registCount = userService.todayRegist();
		return SysResult.oK(registCount);
	}
	
	//最新注册用户 limit 5 List表
	@RequestMapping("/manage/regist/fiveNew")
	public SysResult  newRegistLimitFive(){
		List<User> userList = userService.newRegistLimitFive();
		return SysResult.oK(userList);
	}
	
	//15.用户修改的接口?
	//参数:无
	//返回参数:
	@RequestMapping("/manage/updateUserInfo")
	public SysResult updateUserInfo(User user){
		//(Map map<String,User>)
		//User user=map.get(user);
		Boolean msg = userService.updateUserInfo(user);
		return SysResult.oK(msg);
	}
		
		
	//16.查询用户的接口?
	//参数:无
	//返回参数:
	@RequestMapping("/manage/selectUserInfo")
	public SysResult selectUserInfo(String email){
		User user = userService.selectUserInfo(email);
		return SysResult.oK(user);
	}
	
	
}

	


































