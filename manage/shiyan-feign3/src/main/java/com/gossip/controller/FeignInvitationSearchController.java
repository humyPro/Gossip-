package com.gossip.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.gossip.service.FeignInvitationService;

import com.gossip.vo.SysResult;





@RestController	
public class FeignInvitationSearchController {
	@Autowired
	private FeignInvitationService feignInvitationService;	
	@RequestMapping("/hello/{q}")
	public SysResult sayHi(@PathVariable("q")String q){						
		SysResult hi=feignInvitationService.sayhi(q);
		return hi;
	}
}
