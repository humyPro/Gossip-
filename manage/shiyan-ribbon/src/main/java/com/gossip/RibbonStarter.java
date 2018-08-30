package com.gossip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableDiscoveryClient
public class RibbonStarter {
	public static void main(String[] args) {		
		SpringApplication.run(RibbonStarter.class, args);				
	}
	
	
	@Bean	//其他代码也可以使用
	@LoadBalanced//这个注解是轮训
	public RestTemplate getResource(){
		return new RestTemplate();
	}
	
	
}
