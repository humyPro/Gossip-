package com.gossip.pojo;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name="comment_info")
public class CommentInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long commentId;
	private String commentText;
	
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	
}

