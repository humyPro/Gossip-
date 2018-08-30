package com.gossip.manage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gossip.manage.pojo.PostVo;
import com.gossip.manage.service.PostService;
import com.gossip.vo.SysResult;

@RestController
public class PostController {

	@Autowired
	private PostService postService;
	
	//说说总数
	@RequestMapping("/manage/post/allPost")
	public SysResult findAllPost(){
		Long allPostCount = postService.findAllPost();
		return SysResult.oK(allPostCount);
	}
	
	//本月发布说说总数
	@RequestMapping("/manage/post/month/allPost")
	public SysResult findMonthPost(){
		Long monthPostCount = postService.findMonthPost();
		return SysResult.oK(monthPostCount);
	}
	
	//今日发布说说总数
	@RequestMapping("/manage/post/today/allPost")
	public SysResult findTodayPost(){
		Long todayPostCount = postService.findTodayPost();
		return SysResult.oK(todayPostCount);
	}
	
	//最近七日每天发布的说说和评论总数[数组]
	@RequestMapping("/manage/week/postAndComment")
	public SysResult everydayPostAndComment(){
		Map<String,Long[]> map = postService.everydayPostAndComment();
		return SysResult.oK(map);
	}
	
	//最新发布的说说 10条
	@RequestMapping("/manage/post/tenNew")
	public SysResult newPostLimitTen(){
		List<PostVo> postList = postService.newPostLimitTen();
		return SysResult.oK(postList);
	}
	
	
	
	
	
	
}
