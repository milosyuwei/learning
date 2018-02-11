package com.weiyu.learning.actuator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by weiyu on 2018/1/19.
 */
@RestController
public class WelcomeController {
    @Resource
    private DiscoveryClient client;

    @Autowired
    private RestTemplate restTemplate;



    @RequestMapping(value = "index")
    public String index(){
        String rtn = restTemplate.getForEntity("http://SERVER-PROVIDER/calculator/add?a=1&b=2", String.class).getBody();
        return rtn;
    }


}
