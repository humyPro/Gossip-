package com.gossip.service;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.mapper.PostMapper;
import com.gossip.mapper.UserMapper;
import com.gossip.pojo.PostVo;
import com.gossip.pojo.User;
import com.gossip.service.HttpClientService;
import com.gossip.vo.SysResult;

@Service
public class DataService {
	public static final ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private HttpClientService client;
	
	//获取管理员信息
	public SysResult getAdminData() {
		SysResult result = new SysResult();
		User user = UserService.getAdminData();
		result.setStatus(200);
		result.setMsg("获取管理员信息");
		result.setData(user);
		return result;
	}
	//获取总用户数
	public Long getTotalUserNum(){
		String totalUSerUrl = "http://manage.gossip.com/manage/findAllUserCount";
		try {
			String totalUserJson = client.doGet(totalUSerUrl);
			SysResult totalUserData = mapper.readValue(totalUserJson, SysResult.class);
			Long totalUserNum =Long.valueOf(totalUserData.getData().toString());
			return totalUserNum;
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		}
	}
	
	//今日在线用户数
	public Long getUserTodayOnline(){
		String todayOnlineUrl = "http://manage.gossip.com/manage/today/online";
		try {
			String todayOnlineJson = client.doGet(todayOnlineUrl);
			SysResult todayOnlineData = mapper.readValue(todayOnlineJson, SysResult.class);
			Long todayOnlineNum = Long.valueOf(todayOnlineData.getData().toString());
			return todayOnlineNum;
		} catch (Exception e) {
			return -1L;
		}
	}
	//今日注册用户数
	public Long getTodayRegistUser(){
		String todayRegistUrl = "http://manage.gossip.com/manage/today/regist";
		try {
			String todayRegistJson = client.doGet(todayRegistUrl);
			SysResult todayRegistData = mapper.readValue(todayRegistJson, SysResult.class);
			Long todayRegistNum = Long.valueOf(todayRegistData.getData().toString());
			return todayRegistNum;
		} catch (Exception e) {
			return -1L;
		}
	}
	//最新注册用户,limit 5
	public List<User> getNewRegistUser(){
		//TODO
		String newRegistUrl = "http://manage.gossip.com/manage/regist/fiveNew";
		try {
			String newRegistJson = client.doGet(newRegistUrl);
			SysResult newRegistData = mapper.readValue(newRegistJson, SysResult.class);
			List<User> userList = (List<User>) newRegistData.getData();
			return userList;
		} catch (Exception e) {
			return null;
		}
	}
	//各种浏览器访问记录
	public Map<String,Object> getBrowser(){
		String browserUrl = "http://manage.gossip.com/manage/browser";
		try {
			String browserJson = client.doGet(browserUrl);
			SysResult browserData = mapper.readValue(browserJson, SysResult.class);
			Map<String,Object> browserNum = (Map) browserData.getData();
			return browserNum;
		} catch (Exception e) {
			return null;
		}
	}
	//获取今日发布说说数
	public Long getTodayPublishPost() {
		String todayPublishPostUrl = "http://manage.gossip.com/manage/post/today/allPost";
		try {
			String todayPublishPostJson = client.doGet(todayPublishPostUrl);
			SysResult todayPublishPostData = mapper.readValue(todayPublishPostJson, SysResult.class);
			Long todayPublishPostNum = (Long)todayPublishPostData.getData();
			return todayPublishPostNum;
		} catch (Exception e) {
			return -1L;
		}
	}
	//获取总说说数
	public Long getTotalPostNum() {
		String browserUrl = "http://manage.gossip.com/manage/post/allPost";
		try {
			String totalPostJson = client.doGet(browserUrl);
			SysResult totalPostData = mapper.readValue(totalPostJson, SysResult.class);
			Long totalPostNum = Long.valueOf(totalPostData.getData().toString());
			return totalPostNum;
		} catch (Exception e) {
			return -1L;
		}
	}
	//获取今日新增评论数
	public Long getNewCommentToday() {
		String newCommentTodayUrl = "http://manage.gossip.com/manage/comment/today/allComment";
		try {
			String newCommentTodayJson = client.doGet(newCommentTodayUrl);
			SysResult newCommentTodayData = mapper.readValue(newCommentTodayJson, SysResult.class);
			Long newCommentTodayNum = Long.valueOf(newCommentTodayData.getData().toString());
			return newCommentTodayNum;
		} catch (Exception e) {
			return -1L;
		}
	}
	//获取总评论数
	public Long getTotalCommentNum() {
		String TotalCommentUrl = "http://manage.gossip.com/manage/comment/allComment";
		try {
			String TotalCommentJson = client.doGet(TotalCommentUrl);
			SysResult TotalCommentData = mapper.readValue(TotalCommentJson, SysResult.class);
			Long TotalCommentNum = Long.valueOf(TotalCommentData.getData().toString());
			return TotalCommentNum;
		} catch (Exception e) {
			return -1L;
		}
	}
	//获取最近7天用户评论分布
	public Map getWeekUser() {
		String weekUserUrl = "http://manage.gossip.com/manage/comment/week/allComment";
		try {
			String weekUserJson = client.doGet(weekUserUrl);
			SysResult weekUserData = mapper.readValue(weekUserJson, SysResult.class);
			Map weekUserNum = (Map) weekUserData.getData();
			return weekUserNum;
		} catch (Exception e) {
			return null;
		}
	}

	//最近七日每天发布的说说和评论总数[数组]
	public Map getWeekPublish() {
		String weekUserUrl = "http://manage.gossip.com/manage/week/postAndComment";
		try {
			String weekUserJson = client.doGet(weekUserUrl);
			SysResult weekUserData = mapper.readValue(weekUserJson, SysResult.class);
			Map weekUserNum = (Map) weekUserData.getData();
			return weekUserNum;
		} catch (Exception e) {
			return null;
		}
	}
	//最新注册用户 5个
	@Autowired
	private UserMapper userMapper;
	public List<User> selectUser() {
		String weekUserUrl = "http://manage.gossip.com/manage/regist/fiveNew";
		try {
			String weekUserJson = client.doGet(weekUserUrl);
			SysResult weekUserData = mapper.readValue(weekUserJson, SysResult.class);
			List<User> userList = (List<User>) weekUserData.getData();
			return userList;
		} catch (Exception e) {
			return null;
		}
	}
	//最新评论的10条
	@Autowired
	private PostMapper postMapper;
	public List<PostVo> selectPostVo() {
		String weekUserUrl = "http://manage.gossip.com/manage/post/tenNew";
		try {
			String weekUserJson = client.doGet(weekUserUrl);
			SysResult weekUserData = mapper.readValue(weekUserJson, SysResult.class);
			List<PostVo> postList = (List<PostVo>) weekUserData.getData();
			return postList;
		} catch (Exception e) {
			return null;
		}
	}
		
		
		
	
	
	
	
	
	

}