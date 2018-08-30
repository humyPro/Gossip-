package com.gossip.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 得到mapper,用于对象,json,字符串之间的转化
 *
 */
public class GetMapper {
	public static ObjectMapper getMapper(){
		ObjectMapper mapper = new ObjectMapper();
		return mapper;
	}
}
