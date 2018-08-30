$(function () {
    var ticket = $.cookie("USER_TICKET");

    if (ticket.length != 0) {
        changeMeau(ticket);
    }
});

function changeMeau(ticket) {
    var image=$.cookie("USER_IMAGE");
    $("#meau_bar").html(
        "<ul class=\"layui-nav pull-left\">" +
        "<li class=\"layui-nav-item layui-this\"><a href=\"index\">首页</a></li>" +
        "<li class=\"layui-nav-item\"><a  href=\"#\" id=\"edit_info_a\" data-toggle=\"modal\" data-target=\"#publish_blogs\" style=\"text-decoration: none\">发布</a></li>"+
        "<li class=\"layui-nav-item\"><a id=\"login_out\" href=\"#\">退出</a></li>" +
        "<li class=\"layui-nav-item\">" +
        "</li>" +
        "</ul>" +
        /*图片需要换乘user.headpic*/
        "<a href=\"/user/person?ticket=" + ticket + "\">" +
        "<img id=\"top_image\" class=\"last_mouse\" src=\""+image+"\">" +
        "</a>"
    );
    //鼠标悬停时样式变化
    $(".last_mouse").mouseover(function () {
        $(this).addClass("get_mouse");
    });
    //鼠标离开时样式变化
    $(".last_mouse").mouseout(function () {
        $(this).removeClass("get_mouse");
    });
    //鼠标点击时进入首页
    $("#top_image").attr("src",image);



    //退出
    $("#login_out").click(function () {
        $.ajax({
            url:"/user/loginout?ticket="+ticket,
            type:"get",
            dataType:"json",
            success:function (result) {
                if(result.status==200){
                    alert("再见！");
                    location.reload(true);
                }else{
                    alert("登出失败！");
                }
            },
            error:function () {

            }
        });
    });
}