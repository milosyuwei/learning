package com.weiyu.learning.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: weiyu
 * @date: 2018/3/8
 */
@ComponentScan
@SpringBootApplication
@EnableFeignClients //用于启动Fegin功能   （无需显式@EnableCircuitBreaker，其已含此功能）
@EnableDiscoveryClient //开启服务发现的能力
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
