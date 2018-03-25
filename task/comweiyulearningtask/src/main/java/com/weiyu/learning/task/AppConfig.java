package com.weiyu.learning.task;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author: weiyu
 * @date: 2018/3/15
 */
@Configuration
public class AppConfig {

    @Bean(name = "schedulerFactoryBean")
    public Scheduler getScheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        /*
        Properties properties = new Properties();
        properties.put("org.quartz.threadPool.threadCount",50);
        factoryBean.setQuartzProperties(
                properties
        );
        */
        return scheduler;
    }
}
