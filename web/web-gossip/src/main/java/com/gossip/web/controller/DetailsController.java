package com.gossip.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gossip.vo.SysResult;
import com.gossip.web.service.DetailsService;


@Controller
public class DetailsController {

	@Autowired
	private DetailsService detailsService;
	public static Long postIds=1L;
	@RequestMapping(value="/web/details/{postId}")
    public String details(@PathVariable Long postId) {
		SysResult result = new SysResult();
		postIds=postId;
		return "details";
	}
	@RequestMapping(value="/web/adddetails/{postId}")
	public String addDetails(@PathVariable String postId,Model model) {
		//查数据库
		//添加到model中
//		model.addAttribute("pinglun", "asdsad");
		return "comment";
	}
	
	//根据ID查说说
	@RequestMapping("/findPostById")
	@ResponseBody
	public SysResult findPostById(){
		
		SysResult result = new SysResult();
		result = this.detailsService.findPostById(postIds);
		return result;
		
	}
	//根据ID查评论
	@RequestMapping("/findComments")
	@ResponseBody
	public SysResult findCommentInfo(){
		SysResult result = new SysResult();
		result = this.detailsService.findCommentInfo(postIds);
		return result;
		
	}
	//新增评论
	@RequestMapping("/insertCommentVo")
	@ResponseBody
	public SysResult insertCommentVo(String commentInfo,Long postId){
		postId=21L;
		SysResult result = new SysResult();
		result = this.detailsService.insertCommentVo(commentInfo,postId);
		return result;
		
	}

}
