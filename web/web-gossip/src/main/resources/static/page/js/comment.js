(function(){
	insertCommentVo();
//	$(".layui-form").submit(function(){
//		
//	});
})();
function insertCommentVo(){
	$.ajax({
		url:"/user/findPostsByPage/",
		type:"get",
		data:{"currentPage":currentPage},
		dataType:"json",
		success:function(result){
			var page = result.data;
			var posts = page.data;
			//清空表格和分页条
			$(".contar-wrap div").html("");
//			for(var i=0;i<posts.length;i++){
				var postvo = posts[0];
				var postValue = postvo.invitation.post;
				if(postValue.length>230){
					postV = postValue.substr(0,230)+"...";
				}else{
					postV = postValue;
				}
				var postimg =postvo.image;
				var publishTime=postvo.publishTime;
				var postPic=postvo.postPic;
				var postId=postvo.postId;
				var tr ='<div class="item" style="border:1px solid #e8e4e4">'+
				'<div class="item-box  layer-photos-demo1 layer-photos-demo">'+
				'<div><img alt="" src="'+postimg+'" style="width:100px;height:80px;">'+
				'<span style="vertical-align:bottom;">'+"<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>"+'发布于：'+publishTime+'</span></div><hr style="height:1px;border:none;border-top:1px dashed #0066CC;margin-top:10px;margin-bottom:5px;" />'+
				'<p>'+postV+'</p>'+
				'<img src="'+postPic+'" alt="" style="width:auto;height:200px;"/>'+
				'<div class="count layui-clear">'+
//					'<span class="pull-left">阅读 <em>100000+</em></span>'+
					'<span class="pull-right like"><i class="layui-icon layui-icon-praise"></i><em>'+postvo.nice+'</em></span>'+
				'</div>'+
			'</div>'+
		'</div>';
				$(".contar-wrap").append(tr);
//			}
		},
		error:function(){
			alert("请求失败");
		}
	});
}