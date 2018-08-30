package com.gossip.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gossip.mapper.PagePostMapper;
import com.gossip.pojo.PostVo;
import com.gossip.util.PageUtil;
import com.gossip.vo.Page;
import com.gossip.vo.SysResult;

@Service
public class Page111Service {
		@Autowired
		private PagePostMapper pagePostMapper;
		@Autowired
		private PageUtil pageUtil;
		@Autowired
		private DataService dataService;
		//说说分页
		public SysResult findPostsByPage(Page page) {
			SysResult result = new SysResult();
			// 设置pageSize
			page.setPageSize(pageUtil.getPageSize());
			// 获取总记录数
			int totalCount =Integer.parseInt(dataService.getTotalPostNum().toString()); //this.pageCommentMapper.getCountByAll(page);
			page.setTotalCount(totalCount);
			System.out.println("pageSize:"+page.getPageSize());
			// 计算总页数
			int totalPage = (totalCount % page.getPageSize()) == 0 ? (totalCount / page.getPageSize())
					: (totalCount / page.getPageSize()) + 1;
			page.setTotalPage(totalPage);
			// 计算前一页
			if (page.getCurrentPage() == 1) {
				page.setPreviousPage(1);
			} else {
				page.setPreviousPage(page.getCurrentPage() - 1);
			}
			// 计算后一页
			if (page.getCurrentPage() == totalPage) {
				page.setNextPage(totalPage);
			} else {
				page.setNextPage(page.getCurrentPage() + 1);
			}
			// 获取当前页的数据
			List<PostVo> users = this.pagePostMapper.findPostsAllByPage(page);
			page.setData(users);
			
			// 计算分页组件条上有多少个数据
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));

			// 能执行到此,证明page里所有的数据都添加完毕,然后把数据封装给Result对象,并返回给页面js
			// System.out.println(page);

			result.setStatus(1);
			result.setData(page);
			return result;
		}
		//评论分页
		public SysResult findCommentsByPage(Page page) {
			SysResult result = new SysResult();
			// 设置pageSize
			page.setPageSize(pageUtil.getPageSize());
			// 获取总记录数
			int totalCount =Integer.parseInt(dataService.getTotalCommentNum().toString()); //this.pageCommentMapper.getCountByAll(page);
			page.setTotalCount(totalCount);
			System.out.println("pageSize:"+page.getPageSize());
			// 计算总页数
			int totalPage = (totalCount % page.getPageSize()) == 0 ? (totalCount / page.getPageSize())
					: (totalCount / page.getPageSize()) + 1;
			page.setTotalPage(totalPage);
			// 计算前一页
			if (page.getCurrentPage() == 1) {
				page.setPreviousPage(1);
			} else {
				page.setPreviousPage(page.getCurrentPage() - 1);
			}
			// 计算后一页
			if (page.getCurrentPage() == totalPage) {
				page.setNextPage(totalPage);
			} else {
				page.setNextPage(page.getCurrentPage() + 1);
			}
			// 获取当前页的数据
			List<PostVo> users = this.pagePostMapper.findPostsAllByPage(page);
			page.setData(users);
			
			// 计算分页组件条上有多少个数据
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));

			// 能执行到此,证明page里所有的数据都添加完毕,然后把数据封装给Result对象,并返回给页面js
			// System.out.println(page);

			result.setStatus(1);
			result.setData(page);
			return result;
		}
}
