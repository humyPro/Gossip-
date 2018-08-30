package com.gossip.manage.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gossip.manage.mapper.UserMapper;
import com.gossip.manage.pojo.User;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	//用户总数
	public Long findAllUserCount() {
		User user = new User();
		int count = userMapper.selectCount(user);
		Long allUserCount = new Long(count);
		return allUserCount;
	}

	//今日上线用户数
	public Long todayOnline() {
		Date d = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String dateNowStr = sdf.format(d);  
        //System.out.println(d);  
        //System.out.println("格式化后的日期：" + dateNowStr); 
		//User user = new User();
		//user.setLastLoginTime(date);
		Long todayOnline = userMapper.selectOnlineCount(dateNowStr);
		return todayOnline;
	}

	//今日注册用户数
	public Long todayRegist() {
		Date d = new Date();  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        String dateNowStr = sdf.format(d); 
		Long todayRegist = userMapper.selectRegistCount(dateNowStr);
		return todayRegist;
	}

	//最新注册用户 limit 5 List表
	public List<User> newRegistLimitFive() {
		List<User> userList = userMapper.newRegistLimitFive();
		return userList;
	}

	public Boolean updateUserInfo(User user) {
		int updateByPrimaryKey = userMapper.updateByPrimaryKey(user);
		if(updateByPrimaryKey==1){
			return true;
		} else {
			return false;
		}
	}

	public User selectUserInfo(String email) {
		User _user = new User();
		_user.setEmail(email);
		User user = userMapper.select(_user).get(0);
		return user;
	}
	
}
