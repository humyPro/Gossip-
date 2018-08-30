package com.gossip.manage.mapper;

import java.util.List;

import com.gossip.manage.pojo.Comment;
import com.gossip.manage.pojo.CommentVo;
import com.gossip.manage.pojo.PostVo;
import com.gossip.manage.vo.Page;
import com.gussop.mapper.MyMapper;

public interface CommentMapper extends MyMapper<Comment>{

	public Long findDayComment(String dateNowStr);

	public List<CommentVo> newCommentLimitTen();

	public Long selectWeekCount(String dateNowStr);

	public Long selectTodayCount(String dateNowStr);

	public List<PostVo> findPostsAllByPage(Page page);

	public List<CommentVo> findCommentVo(Long postId);

	public Integer insertComment(Comment comment);

	public Long findMaxId();

}
