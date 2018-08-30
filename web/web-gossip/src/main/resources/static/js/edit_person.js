$(function () {
    var f1;
    var oldName=$("#edit_username").val();
    $("#edit_username").blur(function () {
        var username = $(this).val();
        username = username.trim();
        var test_username = /^((?!@).)*$/g.test(username);
        //替换汉字验证长度
        var repName = username.replace(/[\u4e00-\u9fa5]/g, "aa");

        if (/^\d+$/g.test(username)) {
            $("#info_span_1").html("用户名不能为纯数字").css({"float": "right", "color": "red"});
            f1 = false;
        } else if (!test_username) {
            f1 = false;
            $("#info_span_1").html("用户名不能有@").css({"float": "right", "color": "red"});
        }
        else if (repName.length < 6 || repName.length > 15) {
            f1 = false;
            $("#info_span_1").html("请输入6-15位字符,或最多7个汉字").css({"float": "right", "color": "red"});
        } else {

            $("#info_span_1").html("");
            if(oldName!=username) {
                f1 = doJSONP(username, 1);
            }
        }
    });

    var f2;
    $("#edit_signed").blur(function () {
        var signed = $(this).val();
        if (signed == null || signed.length > 20) {
            $("#info_span_5").html("个性签名太长").css({"float": "right", "color": "red"});
            f2 = false;
        } else {
            $("#info_span_5").html("");
            f2 = true;
        }
    });

    $("#edit_info_form").submit(function () {

        if(f1&&f2){
            var username=$("#edit_username").val().trim();
            var age=$("#edit_age").val().trim();
            var gender=$("input[name='gender']:checked").val();
            var province=$("#edit_provice").val();
            var city=$("#edit_city").val();
            var district=$("#edit_district").val();
            var signed=$("#edit_signed").val();

            var location;
            if(!$.isEmptyObject(province)){
                location=province;
            }
            if(!$.isEmptyObject(city)){
                location=location+"-"+city;
            }
            if($.isEmptyObject(district)){
                location=location+"-"+district;
            }
            var ticket=$.cookie("USER_TICKET");
            $.ajax({
                url:"/user/update?ticket="+ticket,
                type: "post",
                dataType: "json",
                data: {"username": username, "age": age, "gender": gender, "location": location,"signed":signed},
                success: function (result) {

                    if (result.status == '200'||result.status == 200) {
                        alert("更新信息成功!O(∩_∩)O哈哈~");
                    } else {
                        alert("更新信息失败，请稍后重试_(:зゝ∠)_");
                    }
                    var newTicket=$.cookie("USER_TICKET");
                   location.href("http://www.gossip.com/user/person?ticket="+newTicket);
                },
                error: function (result) {
                    alert("服务器异常，请求失败 ┗( T﹏T )┛");
                    var newTicket=$.cookie("USER_TICKET");
                    location.href("http://www.gossip.com/user/person?ticket="+newTicket);
                }

            });
        }else{
            $("#info_span_1").html("请正确填写信息").css({"float": "right", "color": "red"});

        }
        return false;
    });

});

function doJSONP(param, type) {
    var f = true;
    var msg = "";
    var num = 1;//<span>标签id
    if (type == 1) {
        msg = "用户名已存在";
        num = 1;
    } else if (type == 2) {
        msg = "电话号码已被注册";
        num = 4;
    } else if (type == 3) {
        msg = "邮箱已被注册";
        num = 5;
    }
    $.ajax({
        url: 'http://sso.gossip.com/user/check/' + param + "/" + type,//可以不是本地域名
        type: 'get',
        catch: false,
        dataType: 'jsonp',  //jsonp格式访问
        jsonp: "callback", //这里定义了callback的参数名称，以便服务获取callback的函数名即getMessage
        jsonpCallback: 'getMessage', //获取数据的函数
        success: function (data) {
            if (data.data == true) {
                $("#info_span_" + num).html(msg).css({"float": "right", "color": "red"});
                f = false;
            } else {
                $("#info_span_" + num).html("");
                f = true;
            }
        },
        error: function () {
            alert("服务器异常，请求失败");
        }
    });
    return f;

}