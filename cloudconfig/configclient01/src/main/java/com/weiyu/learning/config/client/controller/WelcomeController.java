package com.weiyu.learning.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weiyu on 2018/1/27.
 */
@RefreshScope
@RestController
public class WelcomeController {
    @Value("${foo}")
    String foo;
    @RequestMapping(value = "index")
    public String index(){
        return foo;
    }
}
