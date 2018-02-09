package com.weiyu.learning.listener;

import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * ApplicationPreparedEvent:spring boot上下文context创建完成，但此时spring中的bean是没有完全加载完成的。
 * @author: weiyu
 * @date: 2018/2/9
 */
public class MyApplicationPreparedEvent implements ApplicationListener<ApplicationPreparedEvent> {

    /**
     * 上下文创建完成后执行的事件监听器
     * @param applicationPreparedEvent
     */
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent applicationPreparedEvent) {
        //获取传递上下文
        ConfigurableApplicationContext cac = applicationPreparedEvent.getApplicationContext();
        System.out.println("ApplicationPreparedEvent 事件");
    }
}
