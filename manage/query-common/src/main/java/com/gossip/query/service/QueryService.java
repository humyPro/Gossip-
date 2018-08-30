package com.gossip.query.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.query.mapper.CommentMapper;
import com.gossip.query.mapper.PostMapper;
import com.gossip.query.mapper.UserMapper;
import com.gossip.query.pojo.Comment;
import com.gossip.query.pojo.CommentVo;
import com.gossip.query.pojo.Post;
import com.gossip.query.pojo.PostVo;
import com.gossip.query.pojo.User;
import com.gossip.redis.RedisService;
import com.gossip.util.GetMapper;



@Service
public class QueryService {

	/*
	 * 首页查询说说
	 */
	@Autowired
	private PostMapper postMapper;


	public List<PostVo> getAllPost(int page, int row) {
		/*// 引入分页插件PageHelper
		PageHelper.startPage(page, row);// 开启查询select拦截
		// 经过了拦截拼接的查询结果,是一个Page对象,page继承了List
		
		
		
		//List<PostVo> postVoList = new ArrayList<PostVo>();
		//List<Post> postList = postMapper.findPostAndInvatation();
		
		List<PostVo> postList = postMapper.findPostAndInvatation();
		
		
		for(Post post:postList){
			Long postId=post.getPostId();
			Invitation it = invitationMapper.findPostAndInvatation(postId);
			PostVo p = new PostVo();
			p.setHeadpic(post.getHeadpic());
			p.setLastEditTime(post.getLastEditTime());
			p.setNice(post.getNice());
			p.setPost(it.getPost());
			p.setPostId(post.getPostId());
			p.setPostPic(post.getPostPic());
			p.setPublishTime(post.getPublishTime());
			p.setUserId(post.getUserId());
			postVoList.add(p);
		}
		
		
		
		//List<Post> postList = postMapper.selectAll();
		// 从page对象中获取数据
		PageInfo<PostVo> pageInfo = new PageInfo<PostVo>(postList);
		// 利用pageInfo将page对象中封装的数据,总数据条数count,和分页结果
		EasyUIResult result = new EasyUIResult();
		result.setTotal((int) pageInfo.getTotal());// 获取count
		result.setRows(pageInfo.getList());*/
		
		List<PostVo> postList = postMapper.findPostAndInvatation();
		System.out.println(postList);
		return postList;
		/*try {
			String jsonData=mapper.writeValueAsString(postList);
			return SysResult.oK(jsonData);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return SysResult.oK("查询失败");
		}*/
		
				
	}
	
	

	/*
	 * 首页根据说说id查询评论
	 */
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private RedisService redisService;

	ObjectMapper mapper = GetMapper.getMapper();

	public List<CommentVo> getCommentByPostId(Long postId) {
		String key = "POST_COMMENT_" + postId;
		if (redisService.exists(key)) {
			try {
				String commentData = redisService.get(key);
				JsonNode data = mapper.readTree(commentData);
				List<CommentVo> commentList = null;
				if (data.isArray() && data.size() > 0) {
					commentList = mapper.readValue(data.traverse(),
							mapper.getTypeFactory().constructCollectionType(List.class, CommentVo.class));
				}
				return commentList;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//List<Comment> commentList = commentMapper.findCommentAndInfoByPostId(postId);
			List<CommentVo> commentList = commentMapper.findCommentAndInfoByPostId(postId);
			try {
				String jsonData = mapper.writeValueAsString(commentList);
				redisService.set(key, jsonData);
				return commentList;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * 个人信息页面所有详情展示
	 */
	@Autowired
	private UserMapper userMapper;

	public String[] getUserpage(Long userId) {

		String userKey = "USER_" + userId;
		String postKey = "USER_POST_" + userId;
		String commentKey = "USER_COMMENT_" + userId;
		String[] jsonAll = new String[3];//以json数组形式返回
		//判断userKey是否存在,存在的话就直接拿,不存在就从数据库拿,然后再存入redis,最后添加到返回的json字符串内
		if (redisService.exists(userKey)) {
			String jsonData = redisService.get(userKey);
			System.out.println(jsonData);
			jsonAll[0]=jsonData;
		} else {
			User _user = userMapper.selectByPrimaryKey(userId);
			try {
				String jsonData = mapper.writeValueAsString(_user);
				System.out.println(jsonData);
				redisService.set(userKey, jsonData);
				jsonAll[0]=jsonData;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//判断postKey是否存在,个人发表的所有说说内容集合
		if (redisService.exists(postKey)) {
			String jsonData = redisService.get(postKey);
			jsonAll[1]=jsonData;
			System.out.println(jsonAll);
		} else {
			Post post =new Post();
			post.setUserId(userId);
			List<Post> postList = postMapper.select(post);
			try {
				String jsonData = mapper.writeValueAsString(postList);
				redisService.set(postKey, jsonData);
				jsonAll[1]=jsonData;
				System.out.println(jsonAll);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//判断commentKey是否存在,个人发表的所有评论内容集合
		if (redisService.exists(commentKey)) {
			String jsonData = redisService.get(commentKey);
			jsonAll[2]=jsonData;
			System.out.println(jsonAll);
		} else {
			Comment comment =new Comment();
			comment.setUserId(userId);
			List<Comment> commentList = commentMapper.select(comment);
			try {
				String jsonData = mapper.writeValueAsString(commentList);
				redisService.set(postKey, jsonData);
				jsonAll[2]=jsonData;
				System.out.println(jsonAll);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return jsonAll;
/*		//判断likeKey是否存在,个人发表的所有点赞的集合
				if (redisService.exists(likeKey)) {
					String jsonData = redisService.get(likeKey);
					jsonAll +=  (jsonData+",");
					System.out.println(jsonAll);
				} else {
					Post post = new Post();
					post.setUserId(userId);
					List<Post> postList = postMapper.select(post);
					try {
						String jsonData = mapper.writeValueAsString(postList);
						redisService.set(postKey, jsonData);
						jsonAll +=  (jsonData+",");
						System.out.println(jsonAll);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}*/

	}

}
















































