package com.weiyu.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Eurekaserver1Application {
	public static void main(String[] args) {
		SpringApplication.run(Eurekaserver1Application.class, args);
	}
}
