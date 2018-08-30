package com.gossip.manage.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gossip.manage.mapper.BrowserMapper;
import com.gossip.manage.pojo.Browser;


@Service
public class BrowserService {

	@Autowired 
	private BrowserMapper browserMapper;

	//各种浏览器访问记录
	public Map<String,Long> browserCount() {
		Map<String,Long> map = new HashMap<String,Long>();
		Browser _browser = new Browser();
		for(Long i=1L;i<6;i++){
			_browser.setBrowserId(i);
			Browser browser = browserMapper.select(_browser).get(0);
			map.put(browser.getBrowserName(), browser.getTimes());
		}
		return map;
	}
	
	
}
