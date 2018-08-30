//@ sourceURL=role.js
$(function() {
	findRoles(1);
	//给搜索按钮添加功能
	$("#rolePanel .row button").click(function(){
		findRoles(1);
	});
	//给新增角色框里的确认按钮添加click事件
	$("#addRole_submit").click(function(){
		return addRole();
	});
	//给修改按钮添加事件
	$("#editRole form").submit(function(){
		return updateRole();
	});
	$("#deleteRole_button").click(function(){
		return deleteRole();
	});
});
function deleteRole(){
	$.ajax({
		url:basepath+"role/deleteRole/"+roleId,
		type:"delete",
		dataType:"json",
		success:function(result){
			if(result.status==1){
				alert(result.message);
				//删行
				$("#role_"+roleId).remove();
				//关闭删除弹出框
				$("#deleteRole").modal("toggle");
			}else{
				alert(result.message);
			}
		},
		error:function(){
			alert("请求失败!");
		}
	});
}
function deleteClick(rId){
	roleId = rId;
}
function updateRole(){
	var newRoleName = $("#role_name").val();
	$.ajax({
		url:basepath+"role/updateRole",
		type:"post",
		data:{"id":roleId,"name":newRoleName},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				alert(result.message);
				//更新修改后的内容
				$("#role_"+roleId).find("td:eq(2)").text(newRoleName);
				//关闭弹出的修改框
				$("#editRole").modal("toggle");
			}else{
				alert(result.message);
			}
		},
		error:function(){
			alert("请求错误!");
		}
	});
	return false;
}
function updateClick(rId){
	roleId = rId;
	var rName = $("#role_"+roleId).find("td:eq(2)").text();
	$("#role_name").val(rName);
}
function addRole(){
	//获取新角色信息
	var roleName=$("#roleName").val();
	$.ajax({
		url:basepath+"role/addRole/"+roleName,
		type:"post",
		dataType:"json",
		success:function(result){
			alert(result.message);
		},
		error:function(){
			alert("请求错误!");
		}
	});
	return false;
}


function findRoles(currentPage) {
	// 检查模糊查询
	var roleKeyword = $("#rolePanel .row input[type='text']").val();
	if (roleKeyword == null || roleKeyword == "") {
		roleKeyword = "undefined";
	}
	$.ajax({
		url:basepath+"role/findRolesByPage",
		type:"get",
		data:{"currentPage":currentPage,"roleKeyword":roleKeyword},
		dataType:"json",
		success:function(result){
			if(result.status==1){
				//查询结果,局部刷新
				//更新表格和分页组件
				$("#role_table tbody").html("");
				$("#role_page").html("");
				var page = result.data;
				var roles = page.data;
				$(roles).each(function(n,value){
					//n:是一个数字,表示循环到第几个对象,从0开始
					//value:是一个对象,是循环到的那个对象
					if(value.name!='超级管理员' && value.name!='学员' && value.name!='讲师'){
						//需要加上删除和修改的超链接
						var tr = '<tr id="role_'+value.id+'">'+
			              '<td>'+(n+1)+'</td>'+
			              '<td>'+value.id+'</td>'+
			              '<td>'+value.name+'</td>'+
			              '<td>'+
			                '<a href="" onclick="updateClick(\''+value.id+'\')" data-toggle="modal" data-target="#editRole" ><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑</a>'+
			                '<a href="" onclick="deleteClick(\''+value.id+'\')" data-toggle="modal" data-target=".bs-example-modal-sm"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除</a>'+
			              '</td>'+
			            '</tr>';
						$("#role_table tbody").append(tr);
					}else{
						//反之不需要加修改和删除的超链接
						var tr = '<tr id="role_'+value.id+'">'+
						'<td>'+(n+1)+'</td>'+
			              '<td>'+value.id+'</td>'+
			              '<td>'+value.name+'</td>'+
			              '<td>'+
			              '</td>'+
			            '</tr>';
						$("#role_table tbody").append(tr);
					}
				});
				//构建分页组件
				if(page.totalPage>1){
					//大于一页才需要显示分页条
					//处理前一页
					var previous ='<li>'+
					'<a href="javascript:findRoles('+page.previousPage+')" aria-label="Previous">'+
					'<span aria-hidden="true">&laquo;</span>'+
					'</a>'+
					'</li>';
					$("#role_page").append(previous);
					//处理中间页
					$(page.aNum).each(function(n,value){
						var middle=' <li><a href="javascript:findRoles('+value+')">'+value+'</a></li>';
						$("#role_page").append(middle);
					});
					//处理后一页
					var next='<li>'+
					'<a href="javascript:findRoles('+page.nextPage+')" aria-label="Next">'+
					'<span aria-hidden="true">&raquo;</span>'+
					'</a>'+
					'</li>';
					$("#role_page").append(next);
				}
			}else if(result.status==0){
				alert("没有查询到数据");
			}
		},
		error:function(){
			alert("请求失败")
		}

	});
}