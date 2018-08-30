package com.gossip.manage.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gossip.manage.mapper.CommentMapper;
import com.gossip.manage.mapper.PostMapper;
import com.gossip.manage.pojo.Post;
import com.gossip.manage.pojo.PostVo;
import com.gossip.manage.pojo.YearAndMonthAndDay;


@Service
public class PostService {

	@Autowired
	private PostMapper postMapper;

	//说说总数
	public Long findAllPost() {
		Post post = new Post();
		int postCount = postMapper.selectCount(post);
		Long allPostCount = new Long(postCount);
		return allPostCount;
	}

	//本月发布说说总数
	
	public Long findMonthPost() {
		Date d = new Date();  
		YearAndMonthAndDay ymd = new YearAndMonthAndDay();
		ymd.setYear(d.getYear());
		ymd.setMonth(d.getMonth());
		Long monthPostCount = postMapper.findMonthPost(ymd);
		return monthPostCount;
	}

	//今日发布说说总数
	public Long findTodayPost() {
		Date d = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String dateNowStr = sdf.format(d); 
		Long dayPostCount = postMapper.findDayPost(dateNowStr);
		return dayPostCount;
	}

	//最近七日每天发布的说说和评论总数[数组]
	@Autowired
	private CommentMapper commentMapper;
	public Map<String, Long[]> everydayPostAndComment() {
		
		Map<String,Long[]> map = new HashMap<String,Long[]>();
		Long[] postCount = new Long[7];
		Long[] commentCount = new Long[7];
		
		for(int i=-6;i<1;i++){
			Calendar cal=Calendar.getInstance();
			cal.add(Calendar.DATE,i);
			Date time=cal.getTime();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String dateNowStr = sdf.format(time); 
	        //System.out.println(dateNowStr);
			postCount[i+6] = postMapper.findDayPost(dateNowStr);
			commentCount[i+6] = commentMapper.findDayComment(dateNowStr);
		}
		map.put("post", postCount);
		map.put("comment", commentCount);
		return map;
	}

	//最新发布的说说 10条
	public List<PostVo> newPostLimitTen() {
		List<PostVo> postVoList = postMapper.newPostLimitTen();
		return postVoList;
	}
	

	
}
