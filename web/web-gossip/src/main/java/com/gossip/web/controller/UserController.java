package com.gossip.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gossip.pojo.User;
import com.gossip.util.CookieUtils;
import com.gossip.vo.SysResult;
import com.gossip.web.service.UserService;

/**
 * @author humy
 * @date 2018/8/4 - 23:11
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    @ResponseBody
    public SysResult userRegister(User user) {
        // TODO 数据校验，但是前端做了，能防君子

        try {
            int count = userService.register(user);
            if (count == 1)
                return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return SysResult.build(201, "注册失败");
    }

    @PostMapping("/user/login")
    @ResponseBody
    public SysResult login(User user, HttpServletRequest request, HttpServletResponse response) {
        //if redis 上没有ticket 就执行登录逻辑，有就取出ticket返回
        //修改密码后要删除redis上的ticke
        return userService.login(user, request, response);
    }

    @GetMapping("/user/person")
    public String goPerson(@RequestParam("ticket") String ticket, Model model, HttpServletRequest request, HttpServletResponse response) {
        //用ticket查user
        User user = userService.queryUser(ticket);
        if (user != null) {
            model.addAttribute("user", user);
            return "person";
        } else {
            CookieUtils.deleteCookie(request, response, "USER_TICKET");
            return "index";
        }
    }


    @PostMapping("/user/update")
    @ResponseBody
    public SysResult updateUSer(@RequestParam("ticket") String ticket, User user, HttpServletRequest request, HttpServletResponse response) {
        return userService.updateUser(ticket, user, request, response);

    }

    @GetMapping("/user/query")
    @ResponseBody
    public SysResult queryUserByTicket(@RequestParam String ticket){
        User user=userService.queryUser(ticket);
        return SysResult.oK(user);
    }




    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        return "hello, " + name;
    }

    @GetMapping("/test")
    public String test(){
        return "testhtml";
    }

    //    @PostMapping("/user/fileupload/{ticket}")
//    @ResponseBody
//    public String changeHeadImange(@PathVariable String ticket, @RequestParam("changePic") MultipartFile file){
//        String image="";
//        System.out.println(ticket);
//        System.out.println("哈哈哈哈");
//       // image=userService.changeHeadImage(ticket,file);
//        return  image;
//    }
//    @PostMapping("/user/fileupload")
//    @ResponseBody
//    public String fileUpload(String ticket,@RequestParam("changePic") MultipartFile picture) {
//        String image="";
//        System.out.println("ahahha "+ticket);
//       // String image = userService.changeHeadImage(ticket, picture);
//        System.out.println(image);
//        if(picture!=null)
//        System.out.println(picture.getOriginalFilename());
//
//        return image;
//    }

}
