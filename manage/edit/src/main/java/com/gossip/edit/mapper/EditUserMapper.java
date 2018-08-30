package com.gossip.edit.mapper;

import com.gossip.pojo.User;
import com.gussop.mapper.MyMapper;

public interface EditUserMapper extends MyMapper<User>{

	public int updateUserImage(User user);

}
