package com.gossip.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gossip.service.FeignDeletePostService;






@Controller	
public class FeignDeletePostController {
	@Autowired
	private FeignDeletePostService feignDeletePostService;

	
	@RequestMapping("/hi2/{postId}")
	@ResponseBody
	public String sayHi(@PathVariable("postId")Long postId){
	
		String hi=feignDeletePostService.sayhi(postId);
		return hi;
	}
}
