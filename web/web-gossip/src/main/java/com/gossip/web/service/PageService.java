package com.gossip.web.service;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.service.HttpClientService;
import com.gossip.vo.HttpResult;
import com.gossip.vo.Page;
import com.gossip.vo.SysResult;

@Service
public class PageService {
		public static final ObjectMapper mapper = new ObjectMapper();
		@Autowired
		private HttpClientService client;
		//说说分页
		public SysResult getTotalPosts(Page page){
			SysResult result = new SysResult();
			String totalUSerUrl = "http://manage.gossip.com/manage/findPagePost";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("page", page);
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
		//评论分页
		public SysResult getTotalComments(Page page){
			SysResult result = new SysResult();
			String totalUSerUrl = "http://manage.gossip.com/manage/findCommentsByPage";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("page", page);
			try {
				HttpResult commentData = client.doPost(totalUSerUrl,params);
				String commentJson = commentData.getBody();
				result = mapper.readValue(commentJson, SysResult.class);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return SysResult.build(201, "请求异常");
			}
		}
}

