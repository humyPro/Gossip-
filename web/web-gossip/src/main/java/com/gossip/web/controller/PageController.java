package com.gossip.web.controller;

import java.util.List;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.Iterator;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.gossip.pojo.Invitation;
import com.gossip.vo.SysResult;
import com.gossip.vo.Page;
import com.gossip.web.service.PageService;

@Controller
public class PageController {
	@Autowired
	private PageService pageService;

	@RequestMapping(value = "/user/findPostsByPage")
	@ResponseBody
	public SysResult findUsersByPage(Page page) {
		SysResult result = new SysResult();
		result = this.pageService.getTotalPosts(page);
		return result;
	};
	@RequestMapping(value = "/user/findCommentsByPage")
	@ResponseBody
	public SysResult findCommentsByPage(Page page) {
		SysResult result = new SysResult();
		result = this.pageService.getTotalComments(page);
		return result;
	};
	public static String titles ="";
	@RequestMapping(value = "/elas")
	public String searchByPage(@RequestParam("title") String title) {
		titles=title;
		return "elas";
	};
	
	@Autowired
	private TransportClient client;
	@RequestMapping(value="/search")
	@ResponseBody
	public SysResult elas() {
		SysResult result11 = new SysResult();
//			System.out.println("---------");
//			System.out.println(titles);
			QueryBuilder query = QueryBuilders.matchQuery("post", titles).operator(Operator.AND);
			SearchResponse response = client.prepareSearch("gossip_invitation").setQuery(query).setSize(10).get();
			ArrayList<Invitation> invitationList = new ArrayList<Invitation>();
			SearchHits hits = response.getHits();
			Iterator<SearchHit> iterator = hits.iterator();
			while (iterator.hasNext()) {
				SearchHit result = iterator.next();
			}
			for (SearchHit hit : hits) {
				Invitation invitation = new Invitation();
				invitation.setPost((String) hit.getSource().get("post"));
				invitation.setPostId(new Long((int)hit.getSource().get("post_id")));
				System.out.println( hit.getSource().get("post"));
				invitationList.add(invitation);
			}
			result11.setStatus(200);
			result11.setData(invitationList);
			return result11;
		}

}
