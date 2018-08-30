package com.gossip.edit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gossip.edit.mapper.DeleteInvitationMapper;
import com.gossip.edit.mapper.DeletePostMapper;
import com.gossip.pojo.Invitation;
import com.gossip.pojo.Post;
import com.gossip.redis.RedisService;

@Service
public class DeletePostService {

	@Autowired
	private DeletePostMapper deletePostMapper;
	@Autowired
	private DeleteInvitationMapper deleteInvitationMapper;
	
	@Autowired
	public RedisService redisService;
	
	public Boolean DeletePost(Long postId) {
		
		Post post = new Post();
		post.setPostId(postId);
		Invitation invitation = new Invitation();
		invitation.setPostId(postId);		
		
		int rows1=0;
		int rows2=0;
		String ticket="POSTVO_"+postId;
		redisService.del(ticket);
		rows1 = deletePostMapper.deleteByPrimaryKey(post);
		rows2 = deleteInvitationMapper.delete(invitation);
						
		if(rows1>0&&rows2>0){
			return true;
		}else{			
			return false;
		}
	}

}
