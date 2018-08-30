package com.gossip.manage.mapper;

import com.gossip.manage.pojo.CommentInfo;
import com.gussop.mapper.MyMapper;

public interface CommentInfoMapper extends MyMapper<CommentInfo>{

	int insertCommentInfo(CommentInfo commentInfo1);
	
}
