package com.weiyu.learning.hystrix.controller;

import com.netflix.hystrix.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by weiyu on 2018/1/29.
 */
@RestController
public class WelcomeController {

    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index() throws Exception{

       String rtn = new HystrixCommand<String>(
               HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hystrix"))
                       .andCommandKey(HystrixCommandKey.Factory.asKey("hystrix_factory"))
                       .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hystrix_factory_threadPoolKey"))
                       .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(20)
                               .withMaximumSize(100)
                               .withMaxQueueSize(500))
                       .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                               .withExecutionTimeoutInMilliseconds(10000)
                               .withCircuitBreakerRequestVolumeThreshold(1000))

       ){
            @Override
            protected String run() throws Exception {
                return "hystrix hello";
            }
        }.run();
        return rtn;
    }
}
