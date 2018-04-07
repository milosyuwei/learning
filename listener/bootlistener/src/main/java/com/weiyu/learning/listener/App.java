package com.weiyu.learning.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: weiyu
 * @date: 2018/2/8
 */

@SpringBootApplication
@EnableAutoConfiguration
public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        log.info("服务开始启动了");

        //使用这个方法的时候，MyStartApplicationListener 不起作用
        //SpringApplication.run(StartApplication.class,args).addApplicationListener(new MyStartApplicationListener());


        SpringApplication app = new SpringApplication(App.class);
        app.addListeners(new MyStartApplicationListener()); //启动
        app.addListeners(new MyApplicationEnvironmentPreparedEventListener()); // 环境准备好
        app.addListeners(new MyApplicationPreparedEvent());  //上下文准备好
        app.run(args);

    }
}
