$(function () {
    $("input").click(function () {
        $("#login_1").html("");
    });

    //每次加载login.html页面的时候，都要取出cookie中的账号和密码
    if ($.cookie("remember") != null) {
        //如果cookie中不为空，则默认勾选记住我
        $("input[name='login_rem']")[0].checked = true;
    }

    $("input[name='login_username']").val($.cookie("remember"));
    $("#login_form").submit(function () {
        login();
        return false;
    });
    //登录成功后页面变化

    // var ticket = $.cookie("USER_TICKET");

    // if (ticket != null && ticket.length != 0) {
    //     changeMeau(ticket);
    // }

});


function login() {
    var login_key = $("input[name='login_username']").val().trim() + '';
    var password = $("input[name='login_password']").val().trim();
    var f1, f2;
    if (login_key.length <= 0) {
        f1 = false;
        $("#login_1").html("账号不能为空").css({"float": "right", "color": "red"});
    } else {
        f1 = true;
        $("#login_1").html("");
    }
    if (password.length <= 0) {
        f2 = false;
        $("#login_2").html("密码不能为空").css({"float": "right", "color": "red"});
    } else {
        f2 = true;
        $("#login_2").html("");
    }

//判断key是用户名还是邮箱还是电话号码
    var numReg = /^\d+$/g;
    var emailReg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$", "g");
    var data;
    //不知道为什么，在if中判断失效，只能写在外面
    var isPhone = numReg.test(login_key);
    if (emailReg.test(login_key)) {
        var email = login_key;
        data = {"email": email, "password": password};

    } else if (isPhone) {
        var phone = login_key;
        data = {"phone": phone, "password": password};
    } else {
        var username = login_key;
        data = {"username": username, "password": password};
    }
    //帐号密码不能为空
    if (f1 && f2) {
        $.ajax({//登录
            url: "/user/login",
            type: "post",
            dataType: "json",
            data: data,//前端已经判断好了，如果是邮箱，data就只封装了邮箱和密码
            success: function (result) {

                if (result.status == 200) {
                    var user = result.data;
                    $.cookie("USER_IMAGE", user.image,{ domain: 'www.gossip.com' ,path:"/"});
                    $("#login_1").html("");
                    var rem = $("input[name='login_rem']").get(0).checked;
                    if (rem == true) {

                        if (user.username != null && user, username.length != 0) {
                            $.cookie('remember', user.username);
                        } else if (user.phone != null && user.phone.length != 0) {
                            $.cookie('remember', user.phone);
                        } else if (user.email != null && user.email.length != 0) {
                            $.cookie('remember', user.email);
                        }
                    } else {
                        $.cookie('remember', null);
                    }

                    location.reload();
                } else {
                    var msg = result.msg;
                    $("#login_1").html(msg).css({"float": "right", "color": "red"});
                }

            },
            error: function () {
                alert("请求失败！(｡˘•ε•˘｡)(｡˘•ε•˘｡)");
            }

        });

    }
}

// function spaceTest(str) {
//     if (str.indexOf(" ") >= 0) {
//         return false;
//     } else {
//         return true;
//     }
// }

function changeMeau(ticket) {
    var image = $.cookie("USER_IMAGE");
    var ticket = $.cookie("USER_TICKET");
//    var image ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1533712271418&di=0ded5de3f7751d06e9a9706011388560&imgtype=0&src=http%3A%2F%2Fimg.tuku.cn%2Ffile_thumb%2F201708%2Fm2017082020084323.jpg";
    $("#meau_bar").html("");
        var tr= "<ul class=\"layui-nav pull-left\">" +
        "<li class=\"layui-nav-item layui-this\"><a href=\"index\">首页</a></li>" +
        "<li class=\"layui-nav-item\"><a href=\"message.html\">留言</a></li>" +
        "<li class=\"layui-nav-item\"><a href=\"about.html\">关于</a></li>" +
        "<li class=\"layui-nav-item\">" +
        "</li>" +
        "</ul>" +
        /*图片需要换乘user.headpic*/
        "<a href=\"/user/person?ticket=" + ticket + "\">" +
        "<img id=\"top_image\" class=\"last_mouse\" src=\"" + image + "\">" +
        "</a>" +
        "<a data-toggle=\"modal\" data-target=\"#login\" href=\"\" class=\"personal pull-left\">\n" +
        "退出\n" +
        "</a>";
        $("#meau_bar").append(tr);
    //鼠标悬停时样式变化
    $(".last_mouse").mouseover(function () {
        $(this).addClass("get_mouse");
    });
    //鼠标离开时样式变化
    $(".last_mouse").mouseout(function () {
        $(this).removeClass("get_mouse");
    });
    $("#top_image").attr("src",image);
    //鼠标点击时进入首页
}

