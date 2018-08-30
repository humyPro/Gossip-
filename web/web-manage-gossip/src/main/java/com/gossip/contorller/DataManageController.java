package com.gossip.contorller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gossip.pojo.PostVo;
import com.gossip.pojo.User;
import com.gossip.service.DataService;
import com.gossip.vo.SysResult;

@Controller
public class DataManageController {
	@Autowired
	private DataService dataService;
	SysResult result = new SysResult();
	//获取管理员信息
	@RequestMapping("getadmindata")
	@ResponseBody
	public SysResult getAdminData(){
		result = dataService.getAdminData();
		return result;
	}
	
	// 获取今日用户活跃度 百分比参数,大于等于0 小于等于100
	@RequestMapping("yonghuhuoyuedu")
	@ResponseBody
	public SysResult userActivation(){
		//今日用户活跃度=今日登陆用户数/总用户数
		//今日登陆用户数
		Long todayOnlineNum = ((DataService) dataService).getUserTodayOnline();
		//总用户数
		Long totalUserNum =dataService.getTotalUserNum();
		Long userActivation =Math.round((todayOnlineNum*1.0/totalUserNum)*100);
		result.setStatus(200);
		result.setMsg("获取今日用户活跃度");
		result.setData(userActivation);
		return result;
	}
	// 获取今日说说占比 百分比参数,大于等于0 小于等于100
	@RequestMapping("/benyueshuoshuozanbi")
	@ResponseBody
	public SysResult mouthPostRate(){
		//今日说说占比=今日发布说说/总说说数
		//今日发布说说数
		Long todayPublishPost=dataService.getTodayPublishPost();
		//总说说数
		Long totalPostNum =dataService.getTotalPostNum();
		Long mouthPostRate = Math.round((todayPublishPost*1.0/totalPostNum)*100);
		result.setStatus(200);
		result.setData(mouthPostRate);
		return result;
	}
	// 今日上线用户数
	@RequestMapping("/onlineusertoday")
	@ResponseBody
	public SysResult onlineUserToday(){
		Long onlineUserToday = dataService.getUserTodayOnline();
		result.setStatus(200);
		result.setMsg("今日上线用户数");
		result.setData(onlineUserToday);
		return result;
	}
	
	// 获取总说说数
	@RequestMapping("/totalshuoshuo")
	@ResponseBody
	public SysResult totalPost(){
		Long totalPost = dataService.getTotalPostNum();
		result.setStatus(200);
		result.setMsg("今日上线用户数");
		result.setData(totalPost);
		return result;
	}
	
	// 获取今日新增用户占比,大于等于0,小于等于100
	@RequestMapping("/addperusertoday")
	@ResponseBody
	public SysResult addUserToday(){
		//今日新增用户占比=今日新增用户/总用户数
		//今日新增用户
		Long newRegistUserNum =dataService.getTodayRegistUser();
		//总用户数
		Long totalUserNum =dataService.getTotalUserNum();
		Long addUserToday = Math.round((newRegistUserNum*1.0/totalUserNum)*100);
		result.setStatus(200);
		result.setData(addUserToday);
		return result;
	}
	
	// 获取今日新增用户数
	@RequestMapping("/addnewusernumtoday")
	@ResponseBody
	public SysResult addNewUserNumToday(){
		Long newRegistUserNum =dataService.getTodayRegistUser();
		result.setStatus(200);
		result.setMsg("今日新增用户数");
		result.setData(newRegistUserNum);
		return result;
	}
	//获取今日评论数
	@RequestMapping("/addcommenttoday")
	@ResponseBody
	public SysResult addCommentToday(){
		Long newCommentToday =dataService.getNewCommentToday();
		result.setStatus(200);
		result.setMsg("今日新增评论数");
		result.setData(newCommentToday);
		return result;
	}
	
	// 获取今日新增评论占比,大于等于0,小于等于100
	@RequestMapping("/addcommentratetoday")
	@ResponseBody
	public SysResult addCommentRateToday(){
		//今日新增用户评论占比= 今日新增用户评论/总评论数
		//今日新增用户评论
		Long newCommentToday = dataService.getNewCommentToday();
		//总评论数
		Long totalComment = dataService.getTotalCommentNum();
		Long addCommentRateToday = Math.round((newCommentToday*1.0/totalComment)*100);
		result.setStatus(200);
		result.setData(addCommentRateToday);
		return result;
	}
	
	// 获取浏览器访问数量
	@RequestMapping("/browser")
	@ResponseBody
	public SysResult browser(){
		Map<String,Object> map = dataService.getBrowser();
		Long chrome = Long.valueOf(map.get("chrome").toString());
		Long firefox = Long.valueOf(map.get("firefox").toString());
		Long safari = Long.valueOf(map.get("safari").toString());
		Long opera = Long.valueOf(map.get("opera").toString());
		Long other = Long.valueOf(map.get("other").toString());
		Long[] num = {chrome,firefox,safari,opera,other};
		result.setStatus(200);
		result.setMsg("获取浏览器访问数量");
		result.setData(num);
		return result;
	}
	// 获取最近7天用户浏览统计
	@RequestMapping("/weekuser")
	@ResponseBody
	public SysResult weekUser(){
		//map中的数组的下标,0对应最远一天,6对应今天
		Map weekUsers = dataService.getWeekUser();
		//前台获取当天日期
		result.setData(weekUsers);
		result.setMsg("获取最近7天用户浏览统计");
		return result;
	}
	
	// 获取最近7天发布统计
	@RequestMapping("/weekpublish")
	@ResponseBody
	public SysResult weekPublish(){
		Map<String,Long[]> weekPublish = dataService.getWeekPublish();
		result.setStatus(200);
		result.setMsg(" 获取最近7天发布统计");
		result.setData(weekPublish);
		return result;
	}
	
	//最新用户
	@RequestMapping("/newUserRegist")
	@ResponseBody
	public SysResult newUserRegist(){
		List<User> userList = dataService.selectUser();
		result.setStatus(200);
		result.setMsg("最新用户");
		result.setData(userList);
		return result;
	}
	
	//最新发布
	@RequestMapping("/newPublishPost")
	@ResponseBody
	public SysResult newPublishPost(){
		List<PostVo> postList = dataService.selectPostVo();
		result.setStatus(200);
		result.setMsg(" 最新发布");
		result.setData(postList);
		return result;
	}
	
	
	
}
