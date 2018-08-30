package com.gossip.manage.mapper;

import java.util.List;

import com.gossip.manage.pojo.User;
import com.gussop.mapper.MyMapper;

public interface UserMapper extends MyMapper<User>{

	public List<User> newRegistLimitFive();

	public Long selectOnlineCount(String dateNowStr);

	public Long selectRegistCount(String dateNowStr);

}
