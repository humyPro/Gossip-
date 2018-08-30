package com.gossip.sso.mapper;

import com.gossip.pojo.User;
import com.gussop.mapper.MyMapper;

import java.util.Map;

/**
 * @author humy
 * @date 2018/8/4 - 23:32
 */
public interface UserMapper extends MyMapper<User> {
    int check(Map<String, String> map);
    int checkEmail(String email);
}
