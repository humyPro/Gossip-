$(function() {
	//最新用户
	newUserRegist();
	//获取管理员信息
	getadmindata();
	//获取今日在线人数
	onlineusertoday();
	// 获取今日新增用户占比
	addperusertoday();
	//获取今日新增用户数
	addnewusernumtoday();
	//获取总说说数
	totalshuoshuo();
	//获取今日新增说说数
	addcommenttoday();
	// 获取今日新增说说占比
	addcommentratetoday();
	//最新说说
	newPublishPost();
	
	
	//给搜索折线图按钮添加click事件
	 $("#chart_line").click(function(){
	 chart_line(1);
	 });
	 //给搜索柱状图按钮添加click事件
	 $("#chart_columnar").click(function(){
		 chart_line(2);
	 });
	 //给搜索饼状图按钮添加click事件
	 $("#chart_pie").click(function(){
		 chart_line(3);
	 });
	 //全部说说查询页面
	 $("#all_post").click(function(){
		 chart_line(4);
	 });
	 //全部评论查询页面
	 $("#all_comment").click(function(){
		 chart_line(5);
	 });
	 //退出
	 $("#logout").click(function(){
		 return logout();
	 });
	
});

//时间格式处理,格式YYYY-MM-DD
function getFormatDate(time) {
	    var now = new Date(time);
	    var year = now.getFullYear(); //getFullYear getYear
	    var month = now.getMonth();
	    var date = now.getDate();
	    var day = now.getDay();
	    var hour = now.getHours();
	    var minu = now.getMinutes();
	    var sec = now.getSeconds();
	    var week;
	    month = month + 1;
	    if (month < 10) month = "0" + month;
	    if (date < 10) date = "0" + date;
	    if (hour < 10) hour = "0" + hour;
	    if (minu < 10) minu = "0" + minu;
	    if (sec < 10) sec = "0" + sec;
	    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	    week = arr_week[day];
	    var time = "";
	    time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu + ":" + sec + " " + week;
	    return time;
	}
//登出
function logout(){
	$.ajax({
		url : "/admin/logout",
		type : "get",
		dataType : "json",
		success : function(result) {
			alert(result.msg);
			window.location.href="/login.html";
		},
		error : function() {
			alert("请求失败");
		}
	});
}
//子页面跳转
function chart_line(id){

	$(".content-page").html("");
	$("#h4text").html("");
	
	if(id===1){
		$(".content-page").load('html/chart_line.html');
	}
	if(id===2){
		$(".content-page").load('html/chart_columnar.html');
	}
	if(id===3){
		$(".content-page").load('html/chart_pie.html');
	}
	if(id===4){
		$(".content-page").load('html/all_post.html');
	}
	if(id===5){
		$(".content-page").load('html/all_comment.html');
	}
	
}



//获取管理员信息
function getadmindata(){
	$.ajax({
		url : "/getadmindata",
		type : "get",
		dataType : "json",
		success : function(result) {
//			 alert("message"+result.message)
			if (result.status == 200) {
				var adm = result.data;
//				 alert(totalusernum)
				$("#admin").html("");
					var div='<div class="user-box am-hide-sm-only">'
						+'<div class="user-img">'
	                		+'<img src="'+adm.image+'" alt="user-img" title="Mat Helme" class="img-circle img-thumbnail img-responsive">'
	                	+'<div class="user-status offline"><i class="am-icon-dot-circle-o" aria-hidden="true"></i></div>'
	                +'</div>'
	                +'<h5><a href="#">'+adm.userRole+':'+adm.username+'</a> </h5>'
	              +'</div>'
	              
	              $("#admin").append(div);
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
}


// 今日上线用户数
function onlineusertoday() {
	$.ajax({
		url : "/onlineusertoday",
		type : "get",
		dataType : "json",
		success : function(result) {
			// alert("message"+result.message)
			if (result.status == 200) {
				var totalusernum = result.data;
				// alert(totalusernum)
				$("#widget-chart-box-1").next().html("");
				var tr = '<h2 class="p-t-10" style="font-size: 25px;">' + totalusernum + '</h2>'
						+ '<p class="text-muted" style="font-size: 12px;">今日活跃用户数</p>'
				$("#widget-chart-box-1").next().append(tr);
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
}

// 获取总说说数
function totalshuoshuo() {
	$.ajax({
		url : "/totalshuoshuo",
		type : "get",
		dataType : "json",
		success : function(result) {
			// alert("message"+result.message)
			if (result.status == 200) {
				var totalusernum = result.data;
				// alert(totalusernum)
				$("#widget-chart-box-2").next().html("");
				var tr = '<h2 class="p-t-10" style="font-size: 25px;">' + totalusernum + '</h2>'
						+ '<p class="text-muted" style="font-size: 12px;">总说说数</p>'
				$("#widget-chart-box-2").next().append(tr);
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
}

// 获取今日新增用户占比
function addperusertoday() {
	$.ajax({
		url : "/addperusertoday",
		type : "get",
		dataType : "json",
		success : function(result) {
			// alert("message"+result.message)
			if (result.status == 200) {
				var rateusertoday = result.data;
				// alert(totalusernum)
				$("#perusertoday").html(rateusertoday+"%");
				// 获取今日新增用户占比进度条
				$("#rateusertoday").html('<div class="am-progress-bar" style="width: '+rateusertoday+'%"></div>');
				/*var tr ='<div class="am-progress-bar" style="width: '+rateusertoday+'%"></div>'
				$("#rateusertoday").append(tr);*/
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
}

// 获取今日新增用户数
function addnewusernumtoday() {
	$.ajax({
		url : "/addnewusernumtoday",
		type : "get",
		dataType : "json",
		success : function(result) {
			// alert("message"+result.message)
			if (result.status == 200) {
				var totalusernum = result.data;
				// alert(totalusernum)
				$("#uh1").html(totalusernum);
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
}

//获取今日新增说说数
function addcommenttoday(){
	$.ajax({
		url : "/addcommenttoday",
		type : "get",
		dataType : "json",
		success : function(result) {
			// alert("message"+result.message)
			if (result.status == 200) {
				var totalusernum = result.data;
				// alert(totalusernum)
				$("#uh2").html(totalusernum);
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
}

// 获取今日新增说说占比
function addcommentratetoday() {
		$.ajax({
			url : "/addcommentratetoday",
			type : "get",
			dataType : "json",
			success : function(result) {
//				 alert("message"+result.message)
				if (result.status == 200) {
					var rateusertoday = result.data;
					// alert(totalusernum)
					$("#pershuoshuotoday").html(rateusertoday+"%");
					// 获取今日新增用户占比进度条
					$("#pershuoshuotoday1").html('<div class="am-progress-bar" style="width: '+rateusertoday+'%"></div>');
					/*var tr ='<div class="am-progress-bar" style="width: '+rateusertoday+'%"></div>'
				$("#rateusertoday").append(tr);*/
				}
			},
			error : function() {
				alert("请求失败");
			}
		});
}

//最新用户
function newUserRegist(){
	$.ajax({
		url : "/newUserRegist",
		type : "get",
		dataType : "json",
		success : function(result) {
			if(result.status==200){
			var users = result.data;
			$("#newRegistUser").html("");
			for(var i=0;i<users.length;i++){
				var user = users[i];
				var signed =user.signed;
				if(signed!=null&&signed.length>13){
					signed = user.signed.substr(0,13)+"...";
				}else{
					signed=user.signed;
				}
//				alert("id:"+user.userId);
//				alert("created:"+user.username);
					var div= '<a href="#">'+
						'<div class="inbox-item">'+
						'<div class="inbox-item-img"><img src="'+user.image+'" class="img-circle" alt="" style="width:auto;height:40px;"></div>'+
						'<p class="inbox-item-author">'+user.username+'</p>'+
						'<p class="inbox-item-text">'+signed+'</p>'+
						'<p class="inbox-item-date">'+getFormatDate(user.created)+'</p>'+
						'</div>'+
						'</a>';
	             $("#newRegistUser").append(div);
				
				}
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
}

//最新说说
function newPublishPost(){
	$.ajax({
		url : "/newPublishPost",
		type : "get",
		dataType : "json",
		success : function(result) {
			if (result.status == 200) {
				var posts = result.data;
				$("#PostValue").html("");
				for(var i=0;i<7;i++){
					var post = posts[i];
					var postValue = post.invitation.post;
					var postValue1;
					if(postValue.length>30){
						postValue1=postValue.substr(0,30)+"...";
					}else{
						postValue1=postValue;
					}
					var div='<tr>'+
                        '<td>'+post.postId+'</td>'+
                        '<td>'+postValue1+'</td>'+
                        '<td>'+post.publishTime+'</td>'+
                        '<td>'+post.lastEditTime+'</td>'+
                        '<td><span class="label label-danger">'+post.nice+'</span></td>'+
                        '<td>'+post.userId+'</td>'+
                    '</tr>'
						
						$("#PostValue").append(div);
				}
			}
		},
		error : function() {
			alert("请求失败");
		}
	});
}
	
	