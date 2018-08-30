package com.gossip.edit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.edit.service.EditUserService;
import com.gossip.pojo.User;
import com.gossip.vo.SysResult;


@RestController
public class EditUserController {
	@Autowired
	private EditUserService editUserservice;
	
	public static final ObjectMapper mapper =new ObjectMapper(); 
	
	@RequestMapping(value="/edit/userinfo",method=RequestMethod.POST)	
	public SysResult editUserInfo(User user) throws Exception{		
		//User user = mapper.readValue(userJson, User.class);		
		Boolean b=editUserservice.editUserInfo(user);
		return SysResult.oK(b);	
	}
	
	
	@RequestMapping("/edit/image")
	public SysResult editUserImage(String urlPath,Long userId){
		Boolean b=editUserservice.editImage(urlPath,userId);
		return SysResult.oK(b);
	}
	

	//测试注意数据库的地址
	
/*	@RequestMapping("/edit/userinfo")	
	public SysResult editUserInfo(User user){			
		Boolean b=editUserservice.editUserInfo(user);
		return SysResult.oK(b);
	
	}*/
	
	
	
	
}


