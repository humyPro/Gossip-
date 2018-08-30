package com.gossip.edit.mapper;

import com.gossip.pojo.Comment;
import com.gussop.mapper.MyMapper;

public interface EditCommentMapper extends MyMapper<Comment>{

	public int updateCommentImage(Comment comment);

}
