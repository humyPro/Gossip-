package com.gossip.service;

import java.util.Date;
import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.gossip.vo.SysResult;


@FeignClient("service-managequery")
public interface FeignManageService {
	
	@RequestMapping(value="/manage/findAllUserCount/",method=RequestMethod.GET)
	public SysResult findAllUserCount();

	@RequestMapping(value="/manage/today/online/",method=RequestMethod.GET)
	public SysResult todayOnline();

	@RequestMapping(value="/manage/today/regist",method=RequestMethod.GET)
	public SysResult todayRegist();

	@RequestMapping(value="/manage/regist/fiveNew",method=RequestMethod.GET)
	public SysResult newRegistLimitFive();

	@RequestMapping(value="/manage/post/allPost",method=RequestMethod.GET)
	public SysResult findAllPost();

	@RequestMapping(value="/manage/post/month/allPost",method=RequestMethod.GET)
	public SysResult findMonthPost();

	@RequestMapping(value="/manage/post/today/allPost",method=RequestMethod.GET)
	public SysResult findTodayPost();

	@RequestMapping(value="/manage/week/postAndComment",method=RequestMethod.GET)
	public SysResult everydayPostAndComment();

	@RequestMapping(value="/manage/post/tenNew",method=RequestMethod.GET)
	public SysResult newPostLimitTen();

	@RequestMapping(value="/manage/comment/allComment",method=RequestMethod.GET)
	public SysResult findAllComment();

	@RequestMapping(value="/manage/comment/week/allComment",method=RequestMethod.GET)
	public SysResult findWeekComment();

	@RequestMapping(value="/manage/comment/today/allComment",method=RequestMethod.GET)
	public SysResult findTodayComment();

	@RequestMapping(value="/manage/comment/tenNew",method=RequestMethod.GET)
	public SysResult newCommentLimitTen();

	@RequestMapping(value="/manage/browser",method=RequestMethod.GET)
	public SysResult browserCount();
	
	@RequestMapping(value="/manage/updateUserInfo",method=RequestMethod.GET)
	public SysResult updateUserInfo(@RequestParam(value="age")String age, @RequestParam(value="email")String email,@RequestParam(value="gender") String gender,
			@RequestParam(value="image")String image, @RequestParam(value="lastLoginIp")String lastLoginIp,
			@RequestParam(value="lastLoginTime")Date lastLoginTime,@RequestParam(value="location")String location, @RequestParam(value="password")String password,
			@RequestParam(value="phone")Long phone, @RequestParam(value="registTime")Date registTime,@RequestParam(value="userId")Long userId,
			@RequestParam(value="username")String username,@RequestParam(value="userRole") String userRole);



	@RequestMapping(value="/manage/findPagePost",method=RequestMethod.GET)
	public SysResult findPostsByPage(@RequestParam(value="aNum")List<Integer> aNum, @RequestParam(value="begin")int begin, 
			@RequestParam(value="currentPage")int currentPage,@RequestParam(value="data")List data,@RequestParam(value="nextPage")int nextPage,
			@RequestParam(value="pageSize")int pageSize, @RequestParam(value="previousPage")int previousPage,
			@RequestParam(value="roleKeyword")String roleKeyword,@RequestParam(value="roleType") String roleType,@RequestParam(value="totalCount") int totalCount,@RequestParam(value="totalPage") int totalPage,
			@RequestParam(value="userKeyword")String userKeyword);

	
	@RequestMapping(value="/manage/findPageComment",method=RequestMethod.GET)
	public SysResult findCommentsByPage(@RequestParam(value="aNum")List<Integer> aNum, @RequestParam(value="begin")int begin, 
			@RequestParam(value="currentPage")int currentPage,@RequestParam(value="data")List data,@RequestParam(value="nextPage")int nextPage,
			@RequestParam(value="pageSize")int pageSize, @RequestParam(value="previousPage")int previousPage,
			@RequestParam(value="roleKeyword")String roleKeyword,@RequestParam(value="roleType") String roleType,@RequestParam(value="totalCount") int totalCount,@RequestParam(value="totalPage") int totalPage,
			@RequestParam(value="userKeyword")String userKeyword);

	@RequestMapping(value="/manage/selectUserInfo",method=RequestMethod.GET)
	public SysResult selectUserInfo(@RequestParam(value="email")String email);

	
	@RequestMapping(value="/manage/findPostVo",method=RequestMethod.GET)
	public SysResult findpostVoByPostId(@RequestParam(value="postId")Long postId);

	@RequestMapping(value="/manage/findCommentVo",method=RequestMethod.GET)
	public SysResult findCommentVo(@RequestParam(value="postId")Long postId);

	@RequestMapping(value="/manage/insertCommentVo",method=RequestMethod.GET)
	public SysResult insertCommentVo(@RequestParam(value="commentInfo")String commentInfo,@RequestParam(value="postId")Long postId);	
	
	
		
}
