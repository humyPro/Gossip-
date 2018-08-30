package com.gossip.manage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gossip.manage.service.BrowserService;
import com.gossip.vo.SysResult;

@RestController
public class BrowserController {

	@Autowired
	private BrowserService browserService;
	
	//各种浏览器访问记录
	@RequestMapping("/manage/browser")
	public SysResult browserCount(){
		Map<String,Long> browserCount = browserService.browserCount();
		return SysResult.oK(browserCount);
	}
}




