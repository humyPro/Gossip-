package com.gossip.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gossip.service.FeignPostService;





@Controller	
public class FeignPostController {
	@Autowired
	private FeignPostService feignPostService;

	
	@RequestMapping("/hi/{postId}")
	@ResponseBody
	public String sayHi(@PathVariable("postId")Long postId){
		/*private Long postId;
		private Long userId;
		private Integer nice;
		private Date publishTime;
		private Date lastEditTime;
		private String postPic;
		private String headpic;
		private String text;*/	
		Long userId=2l;
		Long nice=3l;
		String text="今天也有好吃的饭啊,快来吃啊";				
		String hi=feignPostService.sayhi(userId,nice,postId,text);
		return hi;
	}
}
