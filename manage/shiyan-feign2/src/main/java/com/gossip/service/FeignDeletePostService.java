package com.gossip.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("service-edit")
public interface FeignDeletePostService {
	
	@RequestMapping(value="/edit/removepost/{postId}",method=RequestMethod.GET)
	public String sayhi(@RequestParam(value="postId")Long postId);

	//  /edit/sendpost

}
