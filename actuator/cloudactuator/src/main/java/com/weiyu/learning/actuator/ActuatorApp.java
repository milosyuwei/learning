package com.weiyu.learning.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@EnableDiscoveryClient //开启服务发现的能力
public class ActuatorApp {

    public static void main(String[] args){
        SpringApplication.run(ActuatorApp.class, args);
    }
}
