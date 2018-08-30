package com.gossip.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("service-edit")
public interface FeignPostService {
	
	@RequestMapping(value="/edit/sendpost",method=RequestMethod.POST)
	public String sayhi(@RequestParam(value="userId")Long userId,@RequestParam(value="nice")Long nice,@RequestParam(value="postId")Long postId,@RequestParam(value="text")String text);


	//  /edit/sendpost

}
