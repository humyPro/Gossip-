(function(){
		findUsers(1);
		$("#elasearch").submit(function(){
			alert("enenene");
				return false;
		});
})();
function findPost(postId){
	$.ajax({
		type:"get",
		url:"/web/details/",
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {postId:postId},
		dataType:"json",
		success:function(result){
		},
		error:function(){
			alert("请求失败!");
		}
	});
}
function findUsers(currentPage){
	$.ajax({
		url:"/search",
		type:"get",
//		data:{"currentPage":currentPage},
		dataType:"json",
		success:function(result){
			$(".contar-wrap").html("");
			$("#user_page").html("");
			var posts = result.data;
//			var posts = page.data;
			//清空表格和分页条
			var tr1=' <h4 class="item-title"><p><i class="layui-icon layui-icon-speaker"></i>公告：<span>欢迎来到闲言碎语</span></p></h4>';
			$(".contar-wrap").append(tr1);
			for(var i=0;i<posts.length;i++){
				var postvo = posts[i];
				var postValue = postvo.post;
				if(postValue.length>230){
					postV = postValue.substr(0,230)+"...";
				}else{
					postV = postValue;
				}
				var tr = '<div class="item" style="border:1px solid #e8e4e4">'+
				'<div class="item-box  layer-photos-demo1 layer-photos-demo">'+
				'<div><img alt="" src="'+'postvo.image'+'" style="width:auto;height:80px;">'+
				'<span style="vertical-align:bottom;">'+"<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>"+'发布人ID：'+postvo.postId+'</span></div><hr style="height:1px;border:none;border-top:1px dashed #0066CC;margin-top:10px;margin-bottom:5px;" />'+
				'<p>'+postV+'</p>'+
				'<img src="'+'postvo.postPic'+'" alt="" style="width:auto;height:200px;"/>'+
//				'<img src="'+postPic+'" alt=""/>'+
				'</div>'+
//				'<div class="comment count">'+
//				'<a href="javascript:findPost('+postId+')">评论</a>'+
//				'<a href="/web/details/'+'postvo.postId'+'">评论</a>'+
//				'<a href="javascript:;" class="like">点赞</a>'+
//				'</div>'+
				'</div>';
//				document.getElementById(".contar-wrap").innerHTML=tr;
				$(".contar-wrap").append(tr);
			}
//			//给分页条添加数据
//			if(page.totalPage>1){
//				//处理分页组件条
//				//处理第一页
//				var previous='<li>'+
//				'<a href="javascript:findUsers(1)" aria-label="Previous">'+
//				'<span aria-hidden="true">首页</span>'+
//				'</a>'+
//				'</li>';
//				$("#user_page").append(previous);
//				//处理前一页
//				var previous='<li>'+
//		            			'<a href="javascript:findUsers('+page.previousPage+')" aria-label="Previous">'+
//		            			'<span aria-hidden="true">&laquo;</span>'+
//		            			'</a>'+
//		            		 '</li>';
//				$("#user_page").append(previous);
//				//处理中间超链接
//				$(page.aNum).each(function(n,value){
//					var middle=' <li><a href="javascript:findUsers('+value+')">'+value+'</a></li>';
//					$("#user_page").append(middle);
//				});
//				//处理后一页
//				var next='<li>'+
//		            		'<a href="javascript:findUsers('+page.nextPage+')" aria-label="Next">'+
//							'<span aria-hidden="true">&raquo;</span>'+
//							'</a>'+
//						'</li>';
//				$("#user_page").append(next);
//				//处理最后一页
//				var next='<li>'+
//				'<a href="javascript:findUsers('+page.totalPage+')" aria-label="Next">'+
//				'<span aria-hidden="true">尾页</span>'+
//				'</a>'+
//				'</li>';
//				$("#user_page").append(next);
//			}
		},
		error:function(){
			alert("请求失败");
		}
	});
}












