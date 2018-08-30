package com.gossip.query.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gossip.query.pojo.CommentVo;
import com.gossip.query.pojo.PostVo;
import com.gossip.query.service.QueryService;
import com.gossip.vo.SysResult;

@Controller
public class QueryController {
	
	/*
	 * 主页查询所有说说
	 */
	
	@Autowired
	private QueryService queryService;
	@RequestMapping("/page/post/{page}/{row}")
	@ResponseBody
	public SysResult getAllPost(@PathVariable("page")Integer page,
			@PathVariable("row")Integer row){
		List<PostVo> postVoList=queryService.getAllPost(page,row);
		return SysResult.oK(postVoList);
	}
	
	
	/*
	 * 主页点击查询评论
	 */
	@RequestMapping("/page/comment/{postId}")
	@ResponseBody
	public SysResult getCommentByPostId(@PathVariable("postId")Long postId){
		List<CommentVo> commentList=queryService.getCommentByPostId(postId);
		return SysResult.oK(commentList);
	}
	
	/*
	 * 个人信息页面的所有详情查询
	 */
	@RequestMapping("userpage/all/{userId}")
	@ResponseBody
	public String[] getUserpage(@PathVariable("userId")Long userId){
		String[] userAllJson=queryService.getUserpage(userId);
		return userAllJson;
	}
	
}
