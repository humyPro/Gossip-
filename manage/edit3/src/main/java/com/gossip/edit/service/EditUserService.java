package com.gossip.edit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.edit.mapper.EditCommentMapper;
import com.gossip.edit.mapper.EditPostMapper;
import com.gossip.edit.mapper.EditUserMapper;
import com.gossip.pojo.Comment;
import com.gossip.pojo.Post;
import com.gossip.pojo.User;
import com.gossip.redis.RedisService;

@Service
public class EditUserService {

	@Autowired
	private EditUserMapper editUserMapper;

	@Autowired
	private EditPostMapper editPostMapper;
	
	@Autowired
	private EditCommentMapper editCommentMapper;
	
	@Autowired
	public RedisService redisService;
	
	
	public static final ObjectMapper mapper =new ObjectMapper();
	
	public Boolean editUserInfo(User user) {
		int rows=0;
			//rows = editUserMapper.insert(user);
		 rows = editUserMapper.updateByPrimaryKey(user);		
	
		try {
			String userJson = mapper.writeValueAsString(user);
			String ticket="USER_"+user.getUserId();
			redisService.set(ticket, userJson);
			
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		
	System.out.println(rows);
		
		if(rows>0){
			return true;
		}else{			
			return false;
		}
		
		
	}

	public Boolean editImage(String urlPath, Long userId) {
		User user = new User();
		user.setImage(urlPath);
		user.setUserId(userId);
		int i = editUserMapper.updateUserImage(user);
		Post post = new Post();
		post.setUserId(userId);
		post.setImage(urlPath);
		int m =editPostMapper.updatePostImage(post);
		Comment comment = new Comment();
		comment.setUserId(userId);
		comment.setImage(urlPath);
		int n = editCommentMapper.updateCommentImage(comment);
		if(i==1){
			return true;
		} else {
			return false;
		}
	}
	
	

	
}
