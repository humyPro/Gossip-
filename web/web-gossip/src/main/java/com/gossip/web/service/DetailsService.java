package com.gossip.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.service.HttpClientService;
import com.gossip.vo.HttpResult;
import com.gossip.vo.SysResult;

@Service
public class DetailsService {

	public static final ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private HttpClientService client;
	//根据ID查说说
	public SysResult findPostById(Long postId) {
		SysResult result = new SysResult();
		String totalUSerUrl = "http://manage.gossip.com/manage/findPostVo";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postId", postId);
		try {
			HttpResult postData = client.doPost(totalUSerUrl,params);
			String postJson = postData.getBody();
			result = mapper.readValue(postJson, SysResult.class);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "请求异常");
		}
	}
	//根据ID查评论
	public SysResult findCommentInfo(Long postId) {
		SysResult result = new SysResult();
		String totalUSerUrl = "http://manage.gossip.com/manage/findCommentVo";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postId", postId);
		try {
			HttpResult postData = client.doPost(totalUSerUrl,params);
			String postJson = postData.getBody();
			result = mapper.readValue(postJson, SysResult.class);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "请求异常");
		}
	}
	//根据ID查说说
	public SysResult insertCommentVo(String commentInfo,Long postId){
		SysResult result = new SysResult();
		String totalUSerUrl = "http://manage.gossip.com/manage/insertCommentVo";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("postId", postId);
		params.put("commentInfo", commentInfo);
		try {
			HttpResult postData = client.doPost(totalUSerUrl,params);
			String postJson = postData.getBody();
			result = mapper.readValue(postJson, SysResult.class);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "请求异常");
		}
	}

}
