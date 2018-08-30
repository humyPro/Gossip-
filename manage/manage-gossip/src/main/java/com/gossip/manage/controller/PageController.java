package com.gossip.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gossip.manage.service.PageService;
import com.gossip.manage.vo.Page;
import com.gossip.vo.SysResult;

@RestController
public class PageController {

	@Autowired 
	private PageService pageService;
	
	@RequestMapping("/manage/findPagePost")
	public SysResult findPostsByPage(Page page){
		SysResult sysResult = pageService.findPostsByPage(page);
		return sysResult;
	}
	
	@RequestMapping("/manage/findPageComment")
	public SysResult findCommentsByPage(Page page){
		SysResult sysResult = pageService.findCommentsByPage(page);
		return sysResult;
	}
	
	
	@RequestMapping("/manage/findPostVo")
	public SysResult findPostVo(Long postId){
		SysResult sysResult= pageService.findpostVoByPostId(postId);
		return sysResult;
	}
	
	
	
	
	
	
}
