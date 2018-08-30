package com.gossip.web.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.pojo.User;
import com.gossip.service.HttpClientService;
import com.gossip.util.CookieUtils;
import com.gossip.vo.HttpResult;
import com.gossip.vo.SysResult;


/**
 * @author humy
 * @date 2018/8/4 - 23:12
 */
@Service
public class UserService {

    @Autowired
    private HttpClientService client;
    @Autowired
    private ObjectMapper objMapper;

    @PostMapping("/user/register")
    public int register(User user) throws Exception {
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        Map<String, Object> param = registerUserToMap(user);
        String url = "http://sso.gossip.com/user/register";
        //执行请求，如果执行不成功则抛出异常
        HttpResult httpResult = client.doPost(url, param);
        String body = httpResult.getBody();
        return Integer.parseInt(body);

    }

    public SysResult login(User user, HttpServletRequest request, HttpServletResponse response) {
//因为client.doPost(url, param)中要遍历map中的所有元素toString，所以不能有null;在后端要把值从新设置为null，不然通用mapper不能使用
        if (user.getUsername() == null)
            user.setUsername("");
        if (user.getPhone() == null)
            user.setPhone("");
        if (user.getEmail() == null)
            user.setEmail("");

        Map<String, Object> param = registerUserToMap(user);
        String url = "http://sso.gossip.com/user/login";

        try {
            HttpResult httpResult = client.doPost(url, param);
            String ticket = httpResult.getBody();

            if (!StringUtils.isEmpty(ticket)) {
                //如果返回的ticket不是空，就添加到cookie中，并返回OK
                CookieUtils.setCookie(request, response, "USER_TICKET", ticket);

                User _user=queryUser(ticket);
//                CookieUtils.deleteCookie(request, response, "USER_IMAGE");
//                CookieUtils.setCookie(request, response, "USER_IMAGE", _user.getImage());
                if(_user!=null)
                     return SysResult.oK(_user);//成功返回user
            } else {
                return SysResult.build(201, "帐号或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "服务器异常,请稍后重试");
    }


    public Map<String, Object> registerUserToMap(User user) {
        Map<String, Object> param = new HashMap<>();
        param.put("username", user.getUsername());
        param.put("password", user.getPassword());
        param.put("phone", user.getPhone());
        param.put("email", user.getEmail());
        return param;
    }

    public User queryUser(String ticket) {
        String url = "http://sso.gossip.com/user/query?ticket=" + ticket;
        try {
            String jsonUser = client.doGet(url);
            if (jsonUser != null&&jsonUser.length()!=0) {
                System.out.println("jsonUser:"+jsonUser);
                User user = objMapper.readValue(jsonUser, User.class);

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    public SysResult updateUser(String ticket, User user, HttpServletRequest request, HttpServletResponse response) {
        String url = "http://sso.gossip.com/user/update?ticket=" + ticket;
        Map<String, Object> param = updateInfoTOMap(user);
        try {
            HttpResult httpResult = client.doPost(url, param);
            String jsonData = httpResult.getBody();
            SysResult result = objMapper.readValue(jsonData, SysResult.class);
            if (result.getStatus() == 200) {
                User _user = objMapper.readValue(result.getData().toString(), User.class);
                String _ticket = DigestUtils.md5Hex(_user.getUsername());
                if (!ticket.equals(_ticket))
                    CookieUtils.setCookie(request, response, "USER_TICKET", _ticket);
                return SysResult.oK(_user);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "服务器异常，更新失败");
    }


    public Map<String, Object> updateInfoTOMap(User user) {
        Map<String, Object> param = new HashMap<>();
        if (user.getUsername() != null)
            param.put("username", user.getUsername());
        if (user.getAge() != null)
            param.put("age", user.getAge());
        if (user.getGender() != null)
            param.put("gender", user.getGender());
        if (user.getLocation() != null)
            param.put("location", user.getLocation());
        if (user.getSigned() != null)
            param.put("signed", user.getSigned());
//        if(user.getImage()!=null)
//            param.put("image", user.getImage());
        return param;
    }



}
