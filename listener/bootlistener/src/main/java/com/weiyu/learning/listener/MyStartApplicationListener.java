package com.weiyu.learning.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: weiyu
 * @date: 2018/2/8
 *
 * Careful with ApplicationStartedEvent.
 * The name is misleading, it's actually the very first event we fire
 * (we should have named this ApplicationStartingEvent and we've done that recently).
 * If you want to check that the application has (fully) started, you may want to use ApplicationReadyEvent.
 * The logging system is initialized at that point so you can safely use loggers.
 */
public class MyStartApplicationListener implements ApplicationListener<ApplicationStartingEvent> {
    private static final Logger log = LoggerFactory.getLogger(MyStartApplicationListener.class);


    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        //spring boot启动开始时执行的事件

        SpringApplication app = applicationStartingEvent.getSpringApplication();
        app.setBannerMode(Banner.Mode.OFF);
        //这个最早，这个时候log配置还没有初始化好，所以使用log不能记录日志。
        log.info("ApplicationStartingEvent系统配置...");



        log.info("ApplicationStartingEvent系统配置加载完成...");
    }
}
