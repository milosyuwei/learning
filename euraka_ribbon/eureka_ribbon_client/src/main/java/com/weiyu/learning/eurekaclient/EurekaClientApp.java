package com.weiyu.learning.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

/**
 * Created by weiyu on 2018/1/19.
 */
@EnableCircuitBreaker //开启断路器 熔断降级机制
@EnableRetry    //启动Retry框架重试机制
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient //开启服务发现的能力
@Configuration
public class EurekaClientApp {



    @Bean //定义REST客户端，RestTemplate实例
    @LoadBalanced //开启负债均衡的能力
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApp.class, args);
    }
}
