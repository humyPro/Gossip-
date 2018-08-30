package com.gossip.image.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.pojo.User;
import com.gossip.service.HttpClientService;
import com.gossip.vo.HttpResult;
import com.gossip.vo.SysResult;

/**
 * @Author humy
 * @Date 2018/8/7 - 14:12
 */
@Service
public class ImageService {
    @Resource
    private HttpClientService client;
    @Resource
    private ObjectMapper objMapper;

    public String changeHeadImage(String ticket, MultipartFile file) {
        String xurl = "http://sso.gossip.com/user/query?ticket=" + ticket;
        String xpath = "";
        try {
            String jsonUser = client.doGet(xurl);
            User user = objMapper.readValue(jsonUser, User.class);
            xpath = DigestUtils.md5Hex(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String realPath = "";
        if (file != null && !file.isEmpty()) {
            try {
                realPath = ResourceUtils.getURL("classpath:").getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            realPath = realPath + "/static/head/" + xpath + "/";
            File filePath = new File(realPath);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            String picName = file.getOriginalFilename();
            File picFile = new File(realPath, picName);

            try {
                file.transferTo(picFile);
                String image = "http://image.gossip.com/head/" + xpath + "/" + picName;
                //写入数据库
                String url = "http://sso.gossip.com/user/update?ticket=" + ticket;
                Map<String, Object> param = new HashMap<>();
                param.put("image", image);

                HttpResult httpResult = client.doPost(url, param);
                SysResult result = objMapper.readValue(httpResult.getBody(), SysResult.class);
                if (result.getStatus() == 200) {
                    return image;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        return null;
    }
}
