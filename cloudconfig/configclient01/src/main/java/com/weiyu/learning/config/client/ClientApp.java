package com.weiyu.learning.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by weiyu on 2018/1/27.
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ClientApp {
    public static void main(String[] args) {
        SpringApplication.run(ClientApp.class, args);
    }

/**
 注意，client的配置文件不能是　application.yml　，必须是　bootstrap　。
 Changed cloud client project application.yml filename to bootstrap.yml port of server works
 bootstrap.yml gets loaded before application.yml
 */
}
