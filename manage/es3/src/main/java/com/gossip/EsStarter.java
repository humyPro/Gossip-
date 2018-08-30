package com.gossip;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class EsStarter {
	
/*	@Bean
	public TransportClient getTransportClient(){
		return new Tr
	}*/
	
	public static void main(String[] args) {		
		SpringApplication.run(EsStarter.class, args);				
	}
	
	
	
	
	
}
