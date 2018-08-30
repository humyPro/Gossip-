package com.gossip.sso.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.pojo.User;
import com.gossip.redis.RedisService;
import com.gossip.sso.mapper.UserMapper;
import com.gossip.util.GetMapper;
import com.gossip.vo.SysResult;

/**
 * @author humy
 * @date 2018/8/5 - 11:27
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisService redis;

    @Resource
    private ObjectMapper objMapper;
    /*@Autowired
    private RedisService redis;*/
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public Boolean check(String param, Integer type) {
        Map<String, String> map = new HashMap<>();
        if (type == 1) {
            map.put("param", "username");
        } else if (type == 2) {
            map.put("param", "phone");
        } else {
            map.put("param", "email");
        }
        map.put("value", param);
        int count = userMapper.check(map);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int register(User user) {
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());
        System.out.println(user);
        try {

            return userMapper.insert(user);

        } catch (Exception e) {
            return 0;
        }

    }

    public String login(User user) {
    	System.out.println("ser");
        String ticket = "";
        String userKey = "";
        if (!StringUtils.isEmpty(user.getUsername())) {
            userKey = DigestUtils.md5Hex(user.getUsername());
        } else if (!StringUtils.isEmpty(user.getPhone())) {
            userKey = DigestUtils.md5Hex(user.getPhone());
        } else if (!StringUtils.isEmpty(user.getEmail())) {
            userKey = DigestUtils.md5Hex(user.getEmail());
        }

        /*
         * 先用userKey去redis上去找，找到了就直接返回，找不到就重新登录，修改密码后要删除redis上的key-value
         * */
        System.out.println("ser11");
        Boolean exists = redis.exists(userKey);
        if (exists) {
            ticket = userKey;
        } else {
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            if (StringUtils.isEmpty(user.getUsername()))
                user.setUsername(null);
            if (StringUtils.isEmpty(user.getPhone()))
                user.setPhone(null);
            if (StringUtils.isEmpty(user.getEmail()))
                user.setEmail(null);

            User _user = userMapper.selectOne(user);

            if (_user != null) {
                ticket = userKey;
                String userJson = "";
                try {
                    userJson = objMapper.writeValueAsString(_user);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                redis.set(userKey, userJson);
            }
        }
        return ticket;
    }

    public User queryUserByTicket(String ticket) {
        String jsonUser = redis.get(ticket);
        System.out.println("jedis"+jsonUser);
        try {
            if(jsonUser!=null) {
                User user = objMapper.readValue(jsonUser, User.class);
                return user;
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    public SysResult userUpdate(String ticket, User user) {
        String jsonUser = redis.get(ticket);
        try {
            User _user = objMapper.readValue(jsonUser, User.class);
            Long userId = _user.getUserId();
            user.setUserId(userId);
            user.setUpdated(new Date());
            int i = userMapper.updateByPrimaryKeySelective(user);
            User newUser=userMapper.selectByPrimaryKey(userId);
            if (i == 1) {
                String value = objMapper.writeValueAsString(newUser);
                String userKey;
                if(user.getUsername()==null){
                    userKey=_user.getUsername();
                }else{
                    userKey=user.getUsername();
                }
                redis.set(DigestUtils.md5Hex(userKey), value);
                if (!_user.getUsername().equals(userKey)) {
                    redis.del(DigestUtils.md5Hex(userKey));
                }
            }
            return SysResult.oK(objMapper.writeValueAsString(user));


        } catch (IOException e) {
            e.printStackTrace();

        }
        return SysResult.build(201, "更新数据失败");
    }
    
	
	/*
	 * 用户登录
	 */
	
	public SysResult login(String email,String password) throws Exception{
		SysResult result = new SysResult();
		String ticket = "";
		User checkUser = new User();
		checkUser.setEmail(email);
		checkUser.setUserRole("管理员");
		int count =userMapper.selectCount(checkUser);//checkEmail(email);
		if(count >= 1){
			// 说明数据库该email存在
			User user = new User();
			user.setEmail(email);
			List<User> list = userMapper.select(user);
			User user1 = list.get(0);
			// 比较密码
			String md5p = DigestUtils.md5Hex(password);
			if(md5p.equals(user1.getPassword())){
				ticket = DigestUtils.md5Hex("USER_TICKET_" + System.currentTimeMillis() +email);
				// email转化成json存入redis
				String emailjson = GetMapper.getMapper().writeValueAsString(user1);
				redis.set(ticket, emailjson);
			}
			result.setStatus(200);
			result.setMsg("登录成功");
			result.setData(ticket);
			
		}
		return result;
	}
	
	public String getEmailJson(String ticket) {
		
		String emailJson = redis.get(ticket);
		return emailJson;
	}
	
	/*
	 * 用户登出
	 */
	public SysResult logout(String key) {
		redis.del(key);
		return SysResult.oK();
	}
}
