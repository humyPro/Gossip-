package com.gossip.image.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author humy
 * @date 2018/8/4 - 21:51
 */
@Configuration
public class BaseConfiguration {
    /*
    * 文件上传的配置
    */
//    @Bean
//    public MultipartResolver multipartResolver(){
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("UTF-8");
//        resolver.setMaxUploadSize(5242880);
//        return resolver;
//    }



}
