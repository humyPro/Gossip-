(function(){
		$("#page").Page({
	  totalPages: 9,//分页总数
	  liNums: 7,//分页的数字按钮数(建议取奇数)
	  activeClass: 'activP', //active 类样式定义
	  callBack : function(page){
			//console.log(page)
		  $.ajax({
				url : "/newPublishPost",
				type : "get",
//				data:{"Page":Page},
				dataType : "json",
				success : function(result) {
					if (result.status == 200) {
						var posts = result.data;
						$("#allPostPage").html("");
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
								
								$("#allPostPage").append(div);
						}
					}
				},
				error : function() {
					alert("请求失败");
				}
			});
	  }
  });
	})();