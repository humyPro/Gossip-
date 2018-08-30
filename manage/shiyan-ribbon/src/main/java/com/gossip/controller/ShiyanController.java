package com.gossip.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.pojo.User;
import com.gossip.service.HttpClientService;
import com.gossip.vo.HttpResult;
import com.gossip.vo.SysResult;


@Controller
public class ShiyanController {

	@Autowired
	private RestTemplate template;
	public static final ObjectMapper mapper =new ObjectMapper();
	
	
	@RequestMapping("/hi/{userId}")
	@ResponseBody
	public String Shiyan(@PathVariable("userId") Long userId) throws Exception{
			
		User user = new User();
		user.setUserId(userId);
		user.setUsername("liuliuliu");
		user.setPhone(12121212l);
		String userJson = mapper.writeValueAsString(user);
		
		SysResult aaa = template.postForObject
		("http://service-edit/edit/userinfo", userJson, SysResult.class);
		Boolean data = (Boolean) aaa.getData();
					
		return data.toString();
	}
	
	
}
