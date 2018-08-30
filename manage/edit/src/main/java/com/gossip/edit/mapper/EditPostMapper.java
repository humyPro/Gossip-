package com.gossip.edit.mapper;

import com.gossip.pojo.Post;
import com.gussop.mapper.MyMapper;

public interface EditPostMapper extends MyMapper<Post>{

	public int updatePostImage(Post post);

}
