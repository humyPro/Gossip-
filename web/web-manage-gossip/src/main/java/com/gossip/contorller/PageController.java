package com.gossip.contorller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gossip.service.PageService;
import com.gossip.vo.Page;
import com.gossip.vo.SysResult;

@Controller
public class PageController {
	@Autowired
	private PageService pageService;

	@RequestMapping(value = "/user/findPostsByPage", method = RequestMethod.GET)
	@ResponseBody
	public SysResult findUsersByPage(Page page) {
		SysResult result = new SysResult();
		result = this.pageService.getTotalPosts(page);
		return result;
	};
	@RequestMapping(value = "/user/findCommentsByPage", method = RequestMethod.GET)
	@ResponseBody
	public SysResult findCommentsByPage(Page page) {
		SysResult result = new SysResult();
		result = this.pageService.getTotalComments(page);
		return result;
	};
}
