package com.gossip.pojo;

import java.sql.Date;

public class PostVo {

	@Override
	public String toString() {
		return "PostVo [postId=" + postId + ", userId=" + userId + ", nice=" + nice + ", publishTime=" + publishTime
				+ ", lastEditTime=" + lastEditTime + ", postPic=" + postPic + ", headpic=" + image + ", invitation="
				+ invitation + "]";
	}
	private Long postId;
	private Long userId;
	private Long nice;
	private Date publishTime;
	private Date lastEditTime;
	private String postPic;
	private String image;
	private Invitation invitation;
	
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
	public Long getNice() {
		return nice;
	}
	public void setNice(Long nice) {
		this.nice = nice;
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

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Invitation getInvitation() {
		return invitation;
	}
	public void setInvitation(Invitation invitation) {
		this.invitation = invitation;
	}
	


}
