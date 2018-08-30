package com.gossip.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gossip.service.HttpClientService;
import com.gossip.vo.PicUploadResult;
import com.gossip.vo.SysResult;



@Controller
@ResponseBody
public class PictureUploadController {

	@Autowired
	private HttpClientService client;
	
	public static ObjectMapper mapper=new ObjectMapper();
	
	@RequestMapping("/pic/upload")
	public Boolean uploadPics(MultipartFile uploadFile,String userId){
		PicUploadResult result =new PicUploadResult();
		//判断后缀
		Boolean b;
		String oldFileName=uploadFile.getOriginalFilename();

		//update user set image=String where 
		
		
		String extFileName = oldFileName.substring(oldFileName.lastIndexOf("."));

		//
		if(!extFileName.matches("^.(jpg|png|gif)$")){			
			b=false;
			return b;
		}
		try{
			//判断木马
			BufferedImage bufImage = ImageIO.read(uploadFile.getInputStream());
			result.setHeight(bufImage.getHeight()+"");
			//没有异常就是图片
			result.setWidth(bufImage.getWidth()+"");

			//images\2018\07\26
			String dir ="/images/"+new SimpleDateFormat("yyyy/MM/dd")
					.format(new Date())+"/";
			//存储在云主机的/home/pic
			
			String path="/home/pics/common"+dir;
			String urlPath="http://image.gossip.com"+dir;
		
			File _dir = new File(path);
			if(!_dir.exists()){
				_dir.mkdirs();//tomcat下的webapp/ROOT/images/.../				
			}
			
			String fileName=System.currentTimeMillis()+""+
					RandomUtils.nextInt(100, 999)+extFileName;
			
			result.setUrl(urlPath+fileName);
			
			//写出磁盘
			uploadFile.transferTo(new File(path+fileName));
			String url="http://edit.gossip.com/edit/image/";
			Map<String,Object> params=new HashMap<String,Object>();
			params.put("urlPath", urlPath);
			params.put("userId", userId);						
			String json = client.doGet(url, params);
			SysResult readValue = mapper.readValue(json, SysResult.class);
			b=true;
			
			//result.setError(0);		
			return b;
			
		}catch(Exception e){
			e.printStackTrace();
			b=false;
			return b;
		}
	}
	
}




