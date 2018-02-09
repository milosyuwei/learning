package com.weiyu.learning.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 一些系统配置，存储在数据库中，需要在项目启动时加载进缓存。此时可以实现ApplicationListener类来实现监听。
 * @author: weiyu
 * @date: 2018/2/8
 */
@Component
public class CommonApplicationListener implements ApplicationListener {
    private static final Logger log = LoggerFactory.getLogger(CommonApplicationListener.class);


    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        log.info("加载系统配置...");



        log.info("系统配置加载完成...");
    }
}

