package com.gossip.manage.mapper;


import java.util.List;

import com.gossip.manage.pojo.CommentVo;
import com.gossip.manage.pojo.Post;
import com.gossip.manage.pojo.PostVo;
import com.gossip.manage.vo.Page;
import com.gossip.manage.pojo.YearAndMonthAndDay;
import com.gussop.mapper.MyMapper;

public interface PostMapper extends MyMapper<Post>{

	public Long findMonthPost(YearAndMonthAndDay dateNowStr);
	
	
	public Long findDayPost(String dateNowStr);


	public List<PostVo> newPostLimitTen();
	

	public List<PostVo> findPostsAllByPage(Page page);


	public PostVo findPostVoByPostId(Long postId);


	public CommentVo findCommentVo(Long postId);





}
