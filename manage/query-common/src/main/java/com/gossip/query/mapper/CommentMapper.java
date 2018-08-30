package com.gossip.query.mapper;

import java.util.List;

import com.gossip.query.pojo.Comment;
import com.gossip.query.pojo.CommentVo;
import com.gussop.mapper.MyMapper;

public interface CommentMapper extends MyMapper<Comment>{


	public List<CommentVo> findCommentAndInfoByPostId(Long postId);
}
