package com.gossip.query.pojo;

import java.io.Serializable;

import javax.persistence.Table;


@Table(name="invitation")
public class Invitation implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private Long postId;
	private String post;
	
	
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String text) {
		this.post = text;
	}
	
	
}
