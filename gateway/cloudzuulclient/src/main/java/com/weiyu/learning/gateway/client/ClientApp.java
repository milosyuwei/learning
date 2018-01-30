package com.weiyu.learning.gateway.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Created by weiyu on 2018/1/25.
 */
//开启Feign功能（无需显式@EnableCircuitBreaker，其已含此功能）
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }
}
