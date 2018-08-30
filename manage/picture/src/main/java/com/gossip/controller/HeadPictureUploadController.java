package com.gossip.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gossip.vo.PicUploadResult;

@Controller
@ResponseBody
public class HeadPictureUploadController {

	@RequestMapping("/pic/head")
	public PicUploadResult uploadPics(MultipartFile uploadFile){
		PicUploadResult result =new PicUploadResult();
		//判断后缀
		
		String oldFileName=uploadFile.getOriginalFilename();

		
		String extFileName = oldFileName.substring(oldFileName.lastIndexOf("."));

		//
		if(!extFileName.matches("^.(jpg|png|gif)$")){
			
			result.setError(1);
			return result;
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
			
			String path="/home/pics/head"+dir;
			String urlPath="http://image.jt.com"+dir;
		
			File _dir = new File(path);
			if(!_dir.exists()){
				_dir.mkdirs();//tomcat下的webapp/ROOT/images/.../
				
			}
			
			String fileName=System.currentTimeMillis()+""+
					RandomUtils.nextInt(100, 999)+extFileName;
			
			result.setUrl(urlPath+fileName);
			
			//写出磁盘
			uploadFile.transferTo(new File(path+fileName));
			result.setError(0);
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
			result.setError(1);
			return result;
		}
	}
	
}




