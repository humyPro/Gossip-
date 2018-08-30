package com.gossip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EnableEurekaServer

public class CenterStart1 {
	
	
	public static void main(String[] args) {
		SpringApplication.run(CenterStart1.class, args);
	}
}
