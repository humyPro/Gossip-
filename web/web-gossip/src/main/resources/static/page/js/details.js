(function(){
	findPosts();
	findComments()
})();
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
function findPosts(){
	$.ajax({
		url:"/findPostById/",
		type:"get",
//		data:{"postId":postId},
		dataType:"json",
		success:function(result){
			var postvo = result.data;
			//清空表格和分页条
			$(".contar-wrap").html("");
				var postValue = postvo.invitation.post;
				var postV;
				if(postValue.length>230){
					postV = postValue.substr(0,230)+"...";
				}else{
					postV = postValue;
				}
				var postimg =postvo.image;
				var publishTime=postvo.publishTime;
				var postPic=postvo.postPic;
				var postId=postvo.postId;
				var tr = '<div class="item" style="border:1px solid #e8e4e4">'+
				'<div class="item-box  layer-photos-demo1 layer-photos-demo">'+
				'<div><img alt="" src="'+postimg+'" style="width:auto;height:80px;">'+
				'<span style="vertical-align:bottom;">'+"<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>"+'发布于：'+publishTime+'</span></div><hr style="height:1px;border:none;border-top:1px dashed #0066CC;margin-top:10px;margin-bottom:5px;" />'+
				'<p>'+postV+'</p>'+
				'<img src="'+postPic+'" alt="" style="width:auto;height:200px;"/>'+
				'</div>'+
//				'<div class="comment count">'+
////				'<a href="javascript:findPost('+postId+')">评论</a>'+
//				'<a href="/web/adddetails/'+postId+'">评论</a>'+
//				'<a href="javascript:;" class="like">点赞</a>'+
//				'</div>'+
				'</div>'+
				'<a name="comment"> </a>'+
				'<div class="comt layui-clear">'+
//					'<a href="" class="pull-left">评论</a>'+
					'<a href="/web/adddetails/'+postId+'" class="pull-right">写评论</a>'+
				'</div>';
				$(".contar-wrap").append(tr);
		},
		error:function(){
			alert("请求失败");
		}
	});
}
function findComments(){
	$.ajax({
		url:"/findComments/",
		type:"get",
//		data:{"currentPage":currentPage},
		dataType:"json",
		success:function(result){
			$(".LAY-msg-box").html("");
			var comments = result.data;
//			var posts = page.data;
			//清空表格和分页条
//			alert(comments);
			for(var i=0;i<comments.length;i++){
			var comment = comments[i];
			var commentValue = comment.commentInfo.commentText;
			if(commentValue.length>230){
				commentV = commentValue.substr(0,230)+"...";
			}else{
				commentV = commentValue;
			}
//			alert("12311");
//			var commentimg =postvo.image;
//			var publishTime=postvo.publishTime;
//			var postPic=postvo.postPic;
//			var postId=postvo.postId;
			var tx ='<div class="info-item" style="border:1px solid #e8e4e4">'+
			'	<img class="info-img" src="../res/static/images/info-img.png" alt=""/>'+
			'	<div class="info-text">'+
				'	<p class="title count">'+
				'		<span class="name">发布时间:'+getFormatDate(comment.commentTime)+'</span>'+
				'		<span class="info-img like"><i class="layui-icon layui-icon-praise"></i>'+'999+'+'</span>'+
				'	</p>'+
				'	<p class="info-intr">'+commentV+'</p>'+
				'</div>'+
			'</div>	';
			$(".contar-wrap").append(tx);
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
}












