package com.gossip.mapper;


import java.util.List;

import com.gossip.pojo.Comment;
import com.gossip.pojo.Post;
import com.gossip.pojo.PostVo;
import com.gossip.vo.Page;
import com.gussop.mapper.MyMapper;

public interface PagePostMapper extends MyMapper<Post>{

	int getCountByAll(Page page);

	List<PostVo> findPostsAllByPage(Page page);

}
