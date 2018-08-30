package com.gossip.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.service.HttpClientService;
import com.gossip.pojo.User;
import com.gossip.vo.HttpResult;
import com.gossip.vo.SysResult;

@Service
public class UserService {
	public static final ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private HttpClientService client;
	@Autowired
	private static User adminUserData = null;
	//获取管理员信息
	public static User getAdminData(){
		
		return adminUserData;
	}
	public String login_shiro(User user, HttpServletRequest request) {
		String lastLoginIp = getIpAddress(request);
		// url地址,对接sso单点登录的接口文件
		String url = "http://sso.gossip.com/admin/login";
		// user信息封装到map中,属性名是key,属性值是value
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", user.getEmail());
		params.put("password", user.getPassword());
		try {
			HttpResult result = client.doPost(url, params);
			String jsonData = result.getBody();
			SysResult sysResult = mapper.readValue(jsonData, SysResult.class);
			String ticket = (String) sysResult.getData();
			// 查询用户的接口
			Map<String, Object> params1 = new HashMap<String, Object>();
			params.put("email", user.getEmail());
			String selectUserData = "http://manage.gossip.com/manage/selectUserInfo";
			HttpResult resultUser = client.doPost(selectUserData,params1);
			String checkUserJson= resultUser.getBody();
			SysResult checkUserData = mapper.readValue(checkUserJson, SysResult.class);
			Object data = checkUserData.getData();
			Map map = mapper.convertValue(data, Map.class);
			// 更新用户的接口*/
			user.setLastLoginTime((new Date()).toString());
			user.setLastLoginIp(lastLoginIp);
			Map<String, Object> pUser = new HashMap<String, Object>();
			pUser.put("user", user);
			String updateUserUrl = "http://manage.gossip.com/manage/updateUserInfo";
			HttpResult updateUserData = client.doPost(updateUserUrl,pUser);
			String userJson = updateUserData.getBody();
			SysResult readValueResult = mapper.readValue(userJson, SysResult.class);
			if(readValueResult.getStatus()!=200){
				throw new Exception();
			}
			//将用户信息放到指定对象中,供前台调用
			//测试内容
			User getUser = new User();
			getUser.setUsername(map.get("username").toString());
			getUser.setImage(map.get("image").toString());
			getUser.setUserRole(map.get("userRole").toString());
			adminUserData = getUser;
			return ticket;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 获取用户登录的ip
	public static String getIpAddress(HttpServletRequest request) {
		String lastLoginIp = null;
		if (request.getHeader("x-forwarded-for") == null) {
			lastLoginIp = request.getRemoteAddr();
		} else {
			if (request.getHeader("x-forwarded-for").length() > 15) {
				String[] aStr = request.getHeader("x-forwarded-for").split(",");
				lastLoginIp = aStr[0];
			} else {
				lastLoginIp = request.getHeader("x-forwarded-for");
			}
		}
		if (lastLoginIp.equals("0:0:0:0:0:0:0:1")) {
			lastLoginIp = "127.0.0.1";
		}

		return lastLoginIp;
	}

	/*
	 * 登出
	 */
	public String doLogout(String key) throws Exception {
		// url地址,对接sso单点登录的接口文件
		String url = "http://sso.gossip.com/admin/logout";
		// user信息封装到map中,属性名是key,属性值是value
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("key", key);
		HttpResult result = client.doPost(url, params);
		return "";
	}

}