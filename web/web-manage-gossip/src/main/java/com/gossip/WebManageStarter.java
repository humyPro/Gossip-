package com.gossip;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gossip.mapper")
public class WebManageStarter {

	public static void main(String[] args) {
		SpringApplication.run(WebManageStarter.class, args);
	}
}
