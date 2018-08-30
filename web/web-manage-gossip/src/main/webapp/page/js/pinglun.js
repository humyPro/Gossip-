(function(){
//	alert("ajax");
	findUsers(1);
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
	    time = year + "." + month + "." + date + " " + hour + ":" + minu + ":" + sec /*+ " " + week*/;
	    return time;
	}
function aaa(){
	alert("请使用超级管理员权限!");
}
function findUsers(currentPage){
//	alert(currentPage);
	$.ajax({
		url:"/user/findCommentsByPage/",
		type:"get",
		data:{"currentPage":currentPage},
		dataType:"json",
		success:function(result){
			//清空表格和分页条
			$("#user_table tbody").html("");
			$("#user_page").html("");
			//给表格添加数据
			var page = result.data;
//			alert("page");
			var comments = page.data;
			for(var i=0;i<comments.length;i++){
				var commentsvo = comments[i];
				var commentValue = commentsvo.commentInfo.commentText;
//				var commentV;
				if(commentValue.length>50){
					commentV = commentValue.substr(0,50)+"...";
				}else{
					commentV = commentValue;
				}
				var tr='<tr>'+
					'<td>'+getFormatDate(commentsvo.commentTime)+'</td>'+
					'<td>'+commentsvo.commentId+'</td>'+
	                '<td>'+commentsvo.postId+'</td>'+
	                '<td>'+commentsvo.userId+'</td>'+
	                /*'<td>'+commentsvo.lastEditTime+'</td>'+*/
	                '<td>'+commentV+'</td>'+
	                '<td style="width:150px">'+
	                  '<a href="javascript:aaa()" data-toggle="modal" data-target="#editUser"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>'+
	                  '<a href="javascript:aaa()" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a>'+
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












