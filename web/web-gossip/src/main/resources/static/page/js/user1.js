//@ sourceURL=user.js
var roleType="all";
var userId;
$(function(){
	console.log("user.js");
	findUsers(1);
	$("#role_type button").click(function(){
		roleType=$(this).text();
		if(roleType=="全部"){
			roleType="all";
		}
		alert(roleType);
		findUsers(1);
	});
//	findAllRoles();
	//给user_tab下第三个的a标签添加click
	$('#user_tab li:eq(2) a').click(function (e) {
		  e.preventDefault()
		 findAllRoles();
	});
	$("#addUserPanel form").submit(function(){
		return addUser();
	});
	//给导出按钮添加click事件
	$("#export_user").click(function(){
		window.location.href=basepath+"user/exprotExcel";
	});
});
function addUser(){
	//获取要添加的新数据
	var loginName=$("#addUserPanel form #inputEmail").val();
	var password=$("#addUserPanel form #inputPassword").val();
	var password2=$("#addUserPanel form #inputPassword2").val();
	var nickName=$("#addUserPanel form #nickName").val();
	var age=$("#addUserPanel form #age").val();
	var roleId=$("#addUserPanel form #roleCategory").val();
	var sex=$("#addUserPanel input[name=user-type]:checked").val();
	
	alert(loginName+","+password+","+password2+','+nickName+','+age+','+roleId+','+sex);
	//重复密码验证
	if(password!=password2){
		alert("两次密码不对!");
		return false;
	}
	//年龄范围判定
	if(age<1){
		alert("年龄不合法!");
		return false;
	}
	//发送异步请求
	$.ajaxFileUpload({
		url:basepath+"user/newUser",
		secureuri:false,
		fileElementId:"addHeadPicture",
		type:"post",
		data:{
			"loginName":loginName,
			"password":password,
			"nickName":nickName,
			"age":age,
			"roleId":roleId,
			"sex":sex
		},
		dataType:"text",
		success:function(data,status){
			alert(data);
			data=data.replace(/<PRE.*?>/g,'');
			data=data.replace("<PRE>",'');
			data=data.replace("</PRE>",'');
			data=data.replace(/<pre.*?>/g,'');
			data=data.replace("<pre>",'');
			data=data.replace("</pre>",'');
			alert(data);
		},
		error:function(){
			alert("请求失败!");
		}
	});
	return false;
}
function findAllRoles(){
	$.ajax({
		url:basepath+"role/findAllRoles",
		type:"get",
		dataType:"json",
		success:function(result){
			if(result.status==1){
				$("#roleCategory").html('');
				var roles = result.data;
				$(roles).each(function(n,role){
					var select_role='<option value="'+role.id+'">'+role.name+'</option>';
					$("#roleCategory").append(select_role);
				});
			};
		},
		error:function(){
			alert("请求错误");
		}
	});
}

function findUsers(currentPage){
	//处理模糊关键字
	var userKeyword = $("#userPanel form input").val();
	if(userKeyword==null || userKeyword==""){
		userKeyword = "undefined";
	}
	
	$.ajax({
		url:basepath+"user/findUsersByPage",
		type:"get",
		data:{"currentPage":currentPage,"userKeyword":userKeyword,"roleType":roleType},
		dataType:"json",
		success:function(result){
			//清空表格和分页条
			$("#user_table tbody").html("");
			$("#user_page").html("");
			//给表格添加数据
			var page = result.data;
			var users = page.data;
			$(users).each(function(index,user){
				var roles = user.roles;
				var roleString = '';
				$(roles).each(function(n,role){
					roleString+=role.name+",";
				});
				if(roleString==''){
					roleString='无';
				}else{
					//有角色,但是要去掉最后一个逗号
					roleString=roleString.substring(0,roleString.length-1);
				}
				var tr='<tr>'+
	                '<td>'+(index+1)+'</td>'+
	                '<td>'+user.loginName+'</td>'+
	                '<td>'+user.nickName+'</td>'+
	                '<td>'+user.loginType+'</td>'+
	                '<td>'+user.score+'</td>'+
	                '<td>'+new Date(user.regDate).toLocaleDateString().replace("/","-").replace("/","-")+'</td>'+
	                '<td>'+user.isLock+'</td>'+
	                '<td>'+roleString+'</td>'+
	                '<td>'+
	                  '<a href="" data-toggle="modal" data-target="#editUser"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>'+
	                  '<a href="" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a>'+
	                '</td>'+
	              '</tr>';
				$("#user_table tbody").append(tr)
			});
			//给分页条添加数据
			if(page.totalPage>1){
				//处理分页组件条
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
			}
		},
		error:function(){
			alert("请求失败");
		}
	});
}













