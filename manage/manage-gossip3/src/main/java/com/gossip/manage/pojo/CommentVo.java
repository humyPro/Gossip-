package com.gossip.manage.pojo;

import java.util.Date;

public class CommentVo {

	private Long commentId;
	private Long postId;
	private Date commentTime;
	private Long userId;
	private String image;
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	private CommentInfo commentInfo;
	
	
	public CommentInfo getCommentInfo() {
		return commentInfo;
	}
	public void setCommentInfo(CommentInfo commentInfo) {
		this.commentInfo = commentInfo;
	}
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getHeadpic() {
		return image;
	}
	public void setHeadpic(String headpic) {
		this.image = headpic;
	}

}
