package com.gossip.pojo;

import java.util.Date;

public class CommentVo {

	private Long commentId;
	private Long postId;
	private Date commentTime;
	private Long userId;
	private String image;
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
