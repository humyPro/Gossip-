package com.gossip.hystirx;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gossip.service.FeignManageService;
import com.gossip.vo.SysResult;

@Component
public class FeignClientFallback implements FeignManageService{

	@Override
	public SysResult findAllUserCount() {
		
		return SysResult.build(400, null);
	}

	@Override
	public SysResult todayOnline() {
		// TODO Auto-generated method stub
		return SysResult.build(400, null);
	}

	@Override
	public SysResult todayRegist() {
		// TODO Auto-generated method stub
		return SysResult.build(400, null);
	}

	@Override
	public SysResult newRegistLimitFive() {
		// TODO Auto-generated method stub
		return SysResult.build(400, null);
	}

	@Override
	public SysResult findAllPost() {
		// TODO Auto-generated method stub
		return SysResult.build(400, null);
	}

	@Override
	public SysResult findMonthPost() {
		// TODO Auto-generated method stub
		return SysResult.build(400, null);
	}

	@Override
	public SysResult findTodayPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult everydayPostAndComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult newPostLimitTen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult findAllComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult findWeekComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult findTodayComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult newCommentLimitTen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult browserCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult updateUserInfo(String age, String email, String gender, String image, String lastLoginIp,
			Date lastLoginTime, String location, String password, Long phone, Date registTime, Long userId,
			String username, String userRole) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult findPostsByPage(List<Integer> aNum, int begin, int currentPage, List data, int nextPage,
			int pageSize, int previousPage, String roleKeyword, String roleType, int totalCount, int totalPage,
			String userKeyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult findCommentsByPage(List<Integer> aNum, int begin, int currentPage, List data, int nextPage,
			int pageSize, int previousPage, String roleKeyword, String roleType, int totalCount, int totalPage,
			String userKeyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult selectUserInfo(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult findpostVoByPostId(Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult findCommentVo(Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysResult insertCommentVo(String commentInfo, Long postId) {
		// TODO Auto-generated method stub
		return null;
	}

}
