package com.gossip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.gossip.redis.RedisService;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.gossip.edit.mapper")
public class EditStarter {	
	@Bean
	public RedisService getRedisService(){
		return new RedisService();
	}
	
	public static void main(String[] args) {		
		SpringApplication.run(EditStarter.class, args);				
	}
}
