package com.gossip.controller;

import java.util.ArrayList;
import java.util.Iterator;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gossip.pojo.Invitation;
import com.gossip.vo.SysResult;

@RestController
public class SearchController {
		
	@Autowired
	private TransportClient client;
	
	@RequestMapping("/search")
	public SysResult searchPosts(String q){				
		QueryBuilder query=QueryBuilders.
				matchQuery("post", q).
				operator(Operator.AND);
		SearchResponse response = client.prepareSearch("gossip_invitation").setQuery(query)
		.setSize(10).get();
		ArrayList<Invitation> invitationList = new ArrayList<Invitation>();
		SearchHits hits = response.getHits();
		Iterator<SearchHit> iterator = hits.iterator();
		
		while(iterator.hasNext()){
			SearchHit result = iterator.next();
		}
		for(SearchHit hit:hits){			
			Invitation invitation=new Invitation();
			invitation.setPost((String)hit.getSource().get("post"));
			invitation.setPostId((Long)hit.getSource().get("postId"));
			invitationList.add(invitation);
		}
		
		return SysResult.oK(invitationList);
		
		
		
		
	}
	
	
}
