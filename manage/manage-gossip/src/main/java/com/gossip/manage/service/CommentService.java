package com.gossip.manage.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gossip.manage.mapper.CommentInfoMapper;
import com.gossip.manage.mapper.CommentMapper;
import com.gossip.manage.mapper.UserMapper;
import com.gossip.manage.pojo.Comment;
import com.gossip.manage.pojo.CommentInfo;
import com.gossip.manage.pojo.CommentVo;
import com.gossip.manage.pojo.User;
import com.gossip.vo.SysResult;

@Service
public class CommentService {

	@Autowired
	private CommentMapper commentMapper;

	//用户评论总数
	public Long findAllComment() {
		Comment comment =new Comment();
		int count = commentMapper.selectCount(comment);
		Long allCommentCount = new Long(count);
		return allCommentCount;
	}

	//本周用户评论总数
	public Long[] findWeekComment() {
		
		Long[] weekCount = new Long[7];
		for(int i=-6;i<1;i++){
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,i);
			//Date d = new Date();  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        Date d = cal.getTime();
	        String dateNowStr = sdf.format(d); 
	        System.out.println(dateNowStr);
	        weekCount[i+6] = commentMapper.selectWeekCount(dateNowStr);
		}
		return weekCount;
	}

	//今日评论总数
	public Long findTodayComment() {
		Date d = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String dateNowStr = sdf.format(d);
        Long todayCommentCount = commentMapper.selectTodayCount(dateNowStr);
		return todayCommentCount;
	}

	//最新的评论 10条
	public List<CommentVo> newCommentLimitTen() {
		List<CommentVo> commentVoList = commentMapper.newCommentLimitTen();
		return commentVoList;
	}

		
	public SysResult findCommentVo(Long postId) {
		
		List<CommentVo> commentvo=commentMapper.findCommentVo(postId);
		System.out.println(commentvo);
		return SysResult.oK(commentvo);
	}

	@Autowired 
	public UserMapper userMapper;
	@Autowired 
	public CommentInfoMapper commentInfoMapper;
	public SysResult insertCommentVo(String commentInfo, Long postId) {
		Comment comment = new Comment();
		User user = userMapper.selectByPrimaryKey(postId);
		comment.setImage(user.getImage());
		System.out.println(user.getImage());
		comment.setUserId(user.getUserId());
        Calendar cal=Calendar.getInstance();
		Date time=cal.getTime();
		System.out.println(time);
        comment.setCommentTime(time);
        comment.setPostId(postId);
        int m =commentMapper.insertComment(comment);
        
        
        Long max = commentMapper.findMaxId();
        CommentInfo commentInfo1 = new CommentInfo();
        commentInfo1.setCommentText(commentInfo);
        commentInfo1.setCommentId(max);
        int n = commentInfoMapper.insertCommentInfo(commentInfo1);
        
        if(m==1 && n==1){
        	return SysResult.oK(true);
        } else {
        	return SysResult.oK(false);
        }
	}

	
}
