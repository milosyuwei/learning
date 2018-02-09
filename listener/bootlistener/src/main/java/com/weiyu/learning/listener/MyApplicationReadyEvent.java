package com.weiyu.learning.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 *
 * ApplicationReadyEvent 该事件表示application应该初始化完成，可以准备接收请求。
 * Event published as late as conceivably possible to indicate that the application is ready to service requests.
 * The source of the event is the SpringApplication itself, but beware of modifying its internal state since all
 * initialization steps will have been completed by then.
 * @author: weiyu
 * @date: 2018/2/9
 */
public class MyApplicationReadyEvent implements ApplicationListener<ApplicationReadyEvent> {


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

    }
}
