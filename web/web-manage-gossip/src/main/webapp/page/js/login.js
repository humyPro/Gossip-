//@ sourceURL=login.js
$(function(){
	console.log("login.js");
	//每次加载login.html，都要从cookie取出用户名，并赋值给用户名框
	$("#inputName").val(getCookie("loginName"));
	$(".container form").submit(function(){
		return login();
	});
});
function login(){
	//获取页面数据
	var loginName=$("#inputName").val();
	var password=$("#inputPassword").val();
	var remember=$(".container form input[type=checkbox]").get(0).checked;
	//根据页面请求，做异步请求
	$.ajax({
		url:basepath+"user/login/"+loginName+"/"+password,
		type:"get",
		datatype:"json",
		success:function(result){
			if(result.status==1){
				//记住用户名
				if(remember){
					addCookie("loginName",loginName,5);
				}
				//登录成功,location是浏览器地址栏的对象，BOM对象
				window.location.href="index.jsp";
			}else if(result.status==0){
				//登录失败
				alert(result.message);
			}
		},
		error:function(){
			alert("请求失败！");
		}
	});
	return false;
}