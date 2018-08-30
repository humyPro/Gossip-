package com.gossip.service;



import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
			String totalPostUrl = "http://manage.gossip.com/manage/findPagePost";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("page", page);
			try {
				HttpResult postData = client.doPost(totalPostUrl,params);
				String jsonData = postData.getBody();
				result = mapper.readValue(jsonData, SysResult.class);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return SysResult.build(201, "请求异常");
			}
		}
		//评论分页
		public SysResult getTotalComments(Page page){
			SysResult result = new SysResult();
			String totalCommmentUrl = "http://manage.gossip.com/manage/findPageComment";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("page", page);
			try {
				HttpResult commentData = client.doPost(totalCommmentUrl,params);
				String jsonData = commentData.getBody();
				result = mapper.readValue(jsonData, SysResult.class);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return SysResult.build(201, "请求异常");
			}
		}
		
		
}