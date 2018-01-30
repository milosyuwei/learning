package com.weiyu.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@EnableDiscoveryClient
@SpringBootApplication
public class Eurekaserver3Application {
	public static void main(String[] args) {
		SpringApplication.run(Eurekaserver3Application.class, args);
	}
}
