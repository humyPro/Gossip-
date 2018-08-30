package com.gossip.edit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.edit.mapper.SendInvitationMapper;
import com.gossip.edit.mapper.SendPostMapper;
import com.gossip.pojo.Invitation;
import com.gossip.pojo.Post;
import com.gossip.pojo.PostVo;
import com.gossip.redis.RedisService;

@Service
public class SendPostService {

	@Autowired
	private SendPostMapper sendPostMapper;
	
	@Autowired
	private SendInvitationMapper sendInvitationMapper;
	
	@Autowired
	public RedisService redisService;
	
	
	@Autowired
	public static final ObjectMapper mapper =new ObjectMapper();
	
	
	public Boolean SavePostVo(PostVo postvo) {
		Post post = new Post();
		Invitation invitation = new Invitation();
		post.setImage(postvo.getHeadpic());
		post.setLastEditTime(postvo.getLastEditTime());
		post.setNice(postvo.getNice());
		post.setPostId(postvo.getPostId());
		post.setPostPic(postvo.getPostPic());
		post.setPublishTime(post.getPublishTime());
		post.setUserId(postvo.getUserId());
		invitation.setPostId(postvo.getPostId());
		invitation.setPost(postvo.getText());
		
		int rows1=0;
		int rows2=0;
		rows1 = sendPostMapper.insert(post); 
		rows2 = sendInvitationMapper.insert(invitation);
		
		try {
			String postvoJson = mapper.writeValueAsString(postvo);
			String ticket="POSTVO_"+postvo.getPostId();
			redisService.set(ticket, postvoJson);		
		} catch (JsonProcessingException e) {			
			e.printStackTrace();
		}
		if(rows1>0&&rows2>0){
			return true;
		}else{
			return false;
		}
				
	}

}
