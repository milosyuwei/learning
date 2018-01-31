package com.weiyu.learning.gateway.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.weiyu.learning.gateway.client.CalculatorService;

import javax.annotation.Resource;

/**
 * Created by weiyu on 2018/1/25.
 */
@RestController
public class ConsumerController {
    @Resource
    private CalculatorService calculatorService;

    @RequestMapping(method = RequestMethod.GET)
    String index(){
        return calculatorService.myadd(2, 3, "gateway");
    }
}
