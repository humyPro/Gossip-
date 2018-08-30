package com.gossip.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gossip.manage.mapper.CommentMapper;
import com.gossip.manage.mapper.PostMapper;
import com.gossip.manage.pojo.CommentVo;
import com.gossip.manage.pojo.PostVo;
import com.gossip.manage.vo.Page;
import com.gossip.manage.vo.PageUtil;
import com.gossip.vo.SysResult;


@Service
public class PageService {
		@Autowired
		private PostMapper postMapper;
		@Autowired
		private PageUtil pageUtil;
		@Autowired
		private PostService postService;
		//说说分页
		public SysResult findPostsByPage(Page page) {
			//Page page = new Page();
			//page.setCurrentPage(1);
			//page.setBegin(1);
			SysResult result = new SysResult();
			// 设置pageSize
			page.setPageSize(pageUtil.getPageSize());
			//System.out.println(pageUtil.getPageSize());
			//System.out.println("11111");
			// 获取总记录数
			Long allCount =postService.findAllPost(); //this.pageCommentMapper.getCountByAll(page);
			//System.out.println(allCount);
			int totalCount =(int)((long)allCount);
			page.setTotalCount(totalCount);
			//System.out.println("pageSize:"+page.getPageSize());
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
			System.out.println(page.getBegin());
			List<PostVo> users = this.postMapper.findPostsAllByPage(page);
			page.setData(users);
			
			// 计算分页组件条上有多少个数据
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));

			// 能执行到此,证明page里所有的数据都添加完毕,然后把数据封装给Result对象,并返回给页面js
			// System.out.println(page);

			result.setStatus(1);
			result.setData(page);
			return result;
		}
		
		
		
		@Autowired
		private CommentService commentService;
		@Autowired
		private CommentMapper commentMapper;
		//评论分页
		public SysResult findCommentsByPage(Page page) {

			SysResult result = new SysResult();
			// 设置pageSize
			page.setPageSize(pageUtil.getPageSize());
			// 获取总记录数
			Long allCount =commentService.findAllComment(); //this.pageCommentMapper.getCountByAll(page);
			int totalCount =(int)((long)allCount);
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
			List<PostVo> users = this.commentMapper.findPostsAllByPage(page);
			page.setData(users);
			
			// 计算分页组件条上有多少个数据
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));

			// 能执行到此,证明page里所有的数据都添加完毕,然后把数据封装给Result对象,并返回给页面js
			// System.out.println(page);

			result.setStatus(1);
			result.setData(page);
			return result;
		}
		
				
		public SysResult findpostVoByPostId(Long postId) {
			// TODO Auto-generated method stub
			PostVo postvo=postMapper.findPostVoByPostId(postId);
			return SysResult.oK(postvo);
		}


		


	
		
		
		
		
		
		
		
		
		
		
		
}
