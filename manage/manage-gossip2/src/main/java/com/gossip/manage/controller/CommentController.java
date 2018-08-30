package com.gossip.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gossip.manage.pojo.CommentVo;
import com.gossip.manage.service.CommentService;
import com.gossip.vo.SysResult;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	//用户评论总数
	@RequestMapping("/manage/comment/allComment")
	public SysResult findAllComment(){
		Long allCommentCount = commentService.findAllComment();
		return SysResult.oK(allCommentCount);
	}
	
	//本周用户评论总数
	@RequestMapping("/manage/comment/week/allComment")
	public SysResult findWeekComment(){
		Long[] weekCommentCount = commentService.findWeekComment();
		return SysResult.oK(weekCommentCount);
	}
	
	//今日评论总数
	@RequestMapping("/manage/comment/today/allComment")
	public SysResult findTodayComment(){
		Long todayCommentCount = commentService.findTodayComment();
		return SysResult.oK(todayCommentCount);
	}
	
	//最新的评论 10条
	@RequestMapping("/manage/comment/tenNew")
	public SysResult newCommentLimitTen(){
		List<CommentVo> commentList = commentService.newCommentLimitTen();
		return SysResult.oK(commentList);
				
	}
	
	@RequestMapping("/manage/findCommentVo")
	public SysResult findCommentInfo(Long postId){
		SysResult sysResult= commentService.findCommentVo(postId);
		return sysResult;
	}
	
	@RequestMapping("/manage/insertCommentVo")
	public SysResult insertCommentVo(String commentInfo,Long postId){
		SysResult sysResult= commentService.insertCommentVo(commentInfo,postId);
		return sysResult;
	}
	
}






























