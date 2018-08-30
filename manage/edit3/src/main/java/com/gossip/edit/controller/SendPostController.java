package com.gossip.edit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gossip.edit.service.SendPostService;
import com.gossip.pojo.PostVo;
import com.gossip.vo.SysResult;

@RestController
public class SendPostController {
	@Autowired
	private SendPostService sendPostService;
	
		@RequestMapping("/edit/sendpost")
			
		public SysResult SendPost(PostVo postvo){
			Boolean b=sendPostService.SavePostVo(postvo);
			return SysResult.oK(b);
		}
	
	
}
