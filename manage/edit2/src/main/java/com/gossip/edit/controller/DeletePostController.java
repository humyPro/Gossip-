package com.gossip.edit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gossip.edit.service.DeletePostService;
import com.gossip.vo.SysResult;

@RestController
public class DeletePostController {
	@Autowired
	private DeletePostService deletePostService;
	
	@RequestMapping("/edit/removepost/{postId}")
	public SysResult DeletePost(@PathVariable Long postId){
		
		Boolean b=deletePostService.DeletePost(postId);
		return SysResult.oK(b);
		
	}
}
