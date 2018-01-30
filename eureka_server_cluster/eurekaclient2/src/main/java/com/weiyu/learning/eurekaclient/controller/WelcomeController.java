package com.weiyu.learning.eurekaclient.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by weiyu on 2018/1/19.
 */
@RestController
public class WelcomeController {
    @Resource
    private DiscoveryClient client;


    @RequestMapping
    public String index(){
        ServiceInstance instance = client.getLocalServiceInstance();
        return String.format("uri=%s，serviceId=%s，result=%s", instance.getUri(), instance.getServiceId(), "Hello World!");
    }
}
