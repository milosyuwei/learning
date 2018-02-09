package com.weiyu.learning.listener;

import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author: weiyu
 * @date: 2018/2/9
 */
public class MyApplicationFailedEvent implements ApplicationListener<ApplicationFailedEvent> {
    /**
     * spring boot启动异常时执行事件
     * @param applicationFailedEvent
     */
    @Override
    public void onApplicationEvent(ApplicationFailedEvent applicationFailedEvent) {
        Throwable throwable = applicationFailedEvent.getException();
        System.out.println("ApplicationFailedEvent 事件");
        //ApplicationContext


        //ConfigurableApplicationContext 定义了 registerShutdownHook 方法.
        //Register a shutdown hook with the JVM runtime, closing this context on JVM shutdown unless it has already been closed at that time.
    }

    /**

     在异常发生时，最好是添加虚拟机对应的钩子进行资源的回收与释放，能友善的处理异常信息。

     在spring boot中已经为大家考虑了这一点，默认情况开启了对应的功能：

     public void registerShutdownHook() {
         if (this.shutdownHook == null) {
             // No shutdown hook registered yet.
             this.shutdownHook = new Thread() {
                @Override
                public void run() {
                    doClose();
                }
             };
             Runtime.getRuntime().addShutdownHook(this.shutdownHook);
         }
     }
     在doClose()方法中进行资源的回收与释放。

     */
}
