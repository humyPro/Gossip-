package com.gossip.query.mapper;

import java.util.List;

import com.gossip.query.pojo.Post;
import com.gossip.query.pojo.PostVo;
import com.gussop.mapper.MyMapper;

public interface PostMapper extends MyMapper<Post>{

	public List<PostVo> findPostAndInvatation();

}
