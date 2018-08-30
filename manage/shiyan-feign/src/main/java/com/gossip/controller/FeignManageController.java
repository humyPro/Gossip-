package com.gossip.controller;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gossip.manage.vo.Page;
import com.gossip.manage.vo.PageUtil;
import com.gossip.pojo.User;
import com.gossip.service.FeignManageService;
import com.gossip.vo.SysResult;




@RestController	
public class FeignManageController {
	@Autowired
	private FeignManageService feignService;

	
	@RequestMapping("/findAllUserCount")
	public SysResult findAllUserCount(){
		
		SysResult hi=feignService.findAllUserCount();
		return hi;
	}

	
	@RequestMapping("/today/online")
	public SysResult todayOnline(){
		return feignService.todayOnline();		
	}

	
	@RequestMapping("/today/regist")
	public SysResult todayRegist(){
		return feignService.todayRegist();
		
	}
	
	@RequestMapping("/regist/fiveNew")
	public SysResult  newRegistLimitFive(){
		return feignService.newRegistLimitFive();
	}
	
	
	@RequestMapping("/post/allPost")
	public SysResult findAllPost(){
		return feignService.findAllPost();
		
	}
	
	@RequestMapping("/post/month/allPost")
	public SysResult findMonthPost(){
		return feignService.findMonthPost();		
	}
	
	@RequestMapping("/post/today/allPost")
	public SysResult findTodayPost(){
		return feignService.findTodayPost();
	}
	
	@RequestMapping("/week/postAndComment")
	public SysResult everydayPostAndComment(){
		return feignService.everydayPostAndComment();
		
	}
	
	@RequestMapping("/post/tenNew")
	public SysResult newPostLimitTen(){
		return feignService.newPostLimitTen();		
	}
	
	@RequestMapping("/comment/allComment")
	public SysResult findAllComment(){
		return feignService.findAllComment();
		
	}
	
	@RequestMapping("/comment/week/allComment")
	public SysResult findWeekComment(){
		return feignService.findWeekComment();
		
	}
	
	@RequestMapping("/comment/today/allComment")
	public SysResult findTodayComment(){
		return feignService.findTodayComment();
		
	}
	
	@RequestMapping("/comment/tenNew")
	public SysResult newCommentLimitTen(){
		return feignService.newCommentLimitTen();
		
				
	}
	
	@RequestMapping("/browser")
	public SysResult browserCount(){
		return feignService.browserCount();
		
	}
	
	@RequestMapping("/updateUserInfo")
	public SysResult updateUserInfo(User user){		
		String age = user.getAge();
		String email = user.getEmail();
		String gender = user.getGender();
		String image = user.getImage();
		String lastLoginIp = user.getLastLoginIp();
		Date lastLoginTime = user.getLastLoginTime();
		String location = user.getLocation();
		String password = user.getPassword();
		Long phone = user.getPhone();
		Date registTime = user.getRegistTime();
		Long userId = user.getUserId();
		String username = user.getUsername();
		String userRole = user.getUserRole();
		
		
		return feignService.updateUserInfo(age,email,gender,image,lastLoginIp,lastLoginTime,location,
				password,phone,registTime,userId,username,userRole);
	
	}
	
	@RequestMapping("/selectUserInfo")
	public SysResult selectUserInfo(String email){
		System.out.println(email);
		return feignService.selectUserInfo(email);

	}
	
	@Autowired
	PageUtil pageUtil;
	
	@RequestMapping("/findPagePost")
	public SysResult findPostsByPage(Page page){
		//public SysResult findPostsByPage(Map<String,Page> map){
		//Page page = map.get("page");
		List<Integer> aNum = page.getaNum();
		int begin = page.getBegin();
		int currentPage = page.getCurrentPage();
		List data = page.getData();
		int nextPage = page.getNextPage();
		int pageSize = page.getPageSize();
		int previousPage = page.getPreviousPage();
		String roleKeyword = page.getRoleKeyword();
		String roleType = page.getRoleType();
		int totalCount = page.getTotalCount();
		
		page.setPageSize(pageUtil.getPageSize());
		//System.out.println(pageUtil.getPageSize());
		int totalPage = page.getTotalPage();
		String userKeyword = page.getUserKeyword();		
		return feignService.findPostsByPage(aNum,begin,currentPage,data,nextPage,pageSize,
				previousPage,roleKeyword,roleType,totalCount,totalPage,userKeyword);
	}
	
	@RequestMapping("/findPageComment")
	public SysResult findCommentsByPage(Page page){
		//public SysResult findCommentsByPage(Map<String,Page> map){
		//Page page = map.get("comment");
		List<Integer> aNum = page.getaNum();
		int begin = page.getBegin();
		int currentPage = page.getCurrentPage();
		List data = page.getData();
		int nextPage = page.getNextPage();
		int pageSize = page.getPageSize();
		int previousPage = page.getPreviousPage();
		String roleKeyword = page.getRoleKeyword();
		String roleType = page.getRoleType();
		int totalCount = page.getTotalCount();
		page.setPageSize(pageUtil.getPageSize());
		int totalPage = page.getTotalPage();
		String userKeyword = page.getUserKeyword();		
		return feignService.findCommentsByPage(aNum,begin,currentPage,data,nextPage,pageSize,
				previousPage,roleKeyword,roleType,totalCount,totalPage,userKeyword);
		
	}
	
	
	@RequestMapping("/findPostVo")
	public SysResult findPostVo(Long postId){
		return feignService.findpostVoByPostId(postId);		
	
	}
	
	@RequestMapping("/findCommentVo")
	public SysResult findCommentInfo(Long postId){
		return feignService.findCommentVo(postId);
	}
	
	@RequestMapping("/insertCommentVo")
	public SysResult insertCommentVo(String commentInfo,Long postId){
		return feignService.insertCommentVo(commentInfo,postId);
		
	}
	
	
	
}
