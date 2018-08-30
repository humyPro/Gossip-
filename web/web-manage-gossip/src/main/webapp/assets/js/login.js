$(function(){
	$(".am-form").submit(function(){
		return login();
	});

});
function login(){
	var email=$("#email").val();
	var password=$("#password").val();
	var remember=$("#remember-me").get(0).checked;
	$.ajax({
		type:"post",
		url:"/dologin",
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
        data: {email:email,password:password},
		dataType:"json",
		success:function(result){
			if(result.status==200){
				//登录成功  location对象是浏览器的地址栏对象
				if(remember){
					//记住密码打对勾 cookie   loginName=wt_zss@126.com
					addCookie("email",email,5);
				}
				window.location.href="/index.html";
				
			}else if(result.status==201){
				//登录失败
				alert("登录失败");
			}
		},
		error:function(){
			alert("请求失败!");
		}

	});
	return false;
}
