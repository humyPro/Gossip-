package com.gossip.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gossip.vo.SysResult;


@FeignClient("service-search")
public interface FeignInvitationService {
	
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public SysResult sayhi(@RequestParam(value="q")String q);


	//  /edit/sendpost

}
