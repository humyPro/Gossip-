package com.gossip.image.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.gossip.image.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author humy
 * @Date 2018/8/7 - 12:59
 */
@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;


    @PostMapping("/user/fileupload")
    @ResponseBody
    public String fileUpload(String ticket,@RequestParam("changePic") MultipartFile picture,String callback) {
         String image = imageService.changeHeadImage(ticket, picture);
        return image;
    }
    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam("name") String name){
        System.out.println("hahah");
        return "hello,"+name;
    }
}
