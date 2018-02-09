package com.weiyu.learning.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author: weiyu
 * @date: 2018/2/8
 */

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("服务开始启动了");

        SpringApplication.run(App.class,args);

    }
}
