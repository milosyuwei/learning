package com.weiyu.learning.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by weiyu on 2018/1/19.
 */
@ComponentScan
@SpringBootApplication
@EnableFeignClients //用于启动Fegin功能   （无需显式@EnableCircuitBreaker，其已含此功能）
@EnableDiscoveryClient //开启服务发现的能力
public class EurekaClientApp {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApp.class, args);
    }
}
