package com.weiyu.learning.socket.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class AppStart {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(AppStart.class,args);
    }
}
