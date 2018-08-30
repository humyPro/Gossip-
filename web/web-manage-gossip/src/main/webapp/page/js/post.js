//@ sourceURL=user.js
//var roleType="all";
//var userId;
//$(function(){
//	alert("1111111");
//	console.log("user.js");
//	findUsers(1);
//	$("#role_type button").click(function(){
//		roleType=$(this).text();
//		if(roleType=="全部"){
//			roleType="all";
//		}
//		alert(roleType);
//		findUsers(1);
//	});
//});
(function(){
	findUsers(1);
})();
function aaa(){
	alert("请使用超级管理员权限!");
}
function findUsers(currentPage){
	$.ajax({
		url:"/user/findPostsByPage/",
		type:"get",
		data:{"currentPage":currentPage},
		dataType:"json",
		success:function(result){
			//清空表格和分页条
			$("#user_table tbody").html("");
			$("#user_page").html("");
			//给表格添加数据
//			Page data = (Page) result.getData();
//			List data2 =data.getData();
//			PostVo postVo = (PostVo) data2.get(0);
//			result.setData(postVo);
			var page = result.data;
			var posts = page.data;
//			$(posts).each(function(index,post){
			var postvo;
			for(var i=0;i<posts.length;i++){
				postvo = posts[i];
				var postValue = postvo.invitation.post;
				if(postValue.length>40){
					postV = postValue.substr(0,40)+"...";
				}else{
					postV = postValue;
				}
				var tr='<tr>'+
					'<td>'+postvo.publishTime+'</td>'+
	                '<td>'+postvo.userId+'</td>'+
	                '<td>'+postvo.postId+'</td>'+
	                '<td>'+postvo.nice+'</td>'+
	                '<td>'+postvo.lastEditTime+'</td>'+
	                '<td>'+postV+'</td>'+
	                '<td style="width:150px">'+
	                  '<a id="" href="javascript:aaa()" data-toggle="modal" data-target="#editUser"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>'+
	                  '<a id="" href="javascript:aaa()" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a>'+
	                '</td>'+
	              '</tr>';
				$("#user_table tbody").append(tr);
			};
			//给分页条添加数据
			if(page.totalPage>1){
				//处理分页组件条
				//处理第一页
				var previous='<li>'+
				'<a href="javascript:findUsers(1)" aria-label="Previous">'+
				'<span aria-hidden="true">首页</span>'+
				'</a>'+
				'</li>';
				$("#user_page").append(previous);
				//处理前一页
				var previous='<li>'+
		            			'<a href="javascript:findUsers('+page.previousPage+')" aria-label="Previous">'+
		            			'<span aria-hidden="true">&laquo;</span>'+
		            			'</a>'+
		            		 '</li>';
				$("#user_page").append(previous);
				//处理中间超链接
				$(page.aNum).each(function(n,value){
					var middle=' <li><a href="javascript:findUsers('+value+')">'+value+'</a></li>';
					$("#user_page").append(middle);
				});
				//处理后一页
				var next='<li>'+
		            		'<a href="javascript:findUsers('+page.nextPage+')" aria-label="Next">'+
							'<span aria-hidden="true">&raquo;</span>'+
							'</a>'+
						'</li>';
				$("#user_page").append(next);
				//处理最后一页
				var next='<li>'+
				'<a href="javascript:findUsers('+page.totalPage+')" aria-label="Next">'+
				'<span aria-hidden="true">尾页</span>'+
				'</a>'+
				'</li>';
				$("#user_page").append(next);
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
}












