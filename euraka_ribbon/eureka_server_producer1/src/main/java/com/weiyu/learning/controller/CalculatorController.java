package com.weiyu.learning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by weiyu on 2018/1/22.
 */
@RestController
@RequestMapping(value="/calculator")
public class CalculatorController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private DiscoveryClient client;

    @RequestMapping("/add")
    public String add(int a, int b) throws Exception{
        //加运算
        int result = a + b;
        //输出服务信息
        ServiceInstance instance = client.getLocalServiceInstance();
        String rtn = String.format("uri=%s，serviceId=%s，result=%s", instance.getUri(), instance.getServiceId(), result);
        logger.info(rtn);
        //返回结果
        //Thread.sleep(2*60*1000);
        throw new Exception("");
        //return rtn;
    }
}
