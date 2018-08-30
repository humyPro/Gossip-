package com.gossip.pojo;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long postId;
	private Long userId;
	private Integer like;
	private Date publishTime;
	private Date lastEditTime;
	private String postPic;
	
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	public String getPostPic() {
		return postPic;
	}
	public void setPostPic(String postPic) {
		this.postPic = postPic;
	}
	

}
