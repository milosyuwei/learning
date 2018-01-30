package com.weiyu.learning.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weiyu on 2018/1/19.
 */
@RestController
@RequestMapping(value="/phone")
public class PhoneController {
    @RequestMapping
    public String index(){
        return "Hello World!";
    }

    @RequestMapping(value="/getmap")
    public Map<String,String> getMap(){
        Map<String,String> phoneMap = new HashMap<String,String>();
        phoneMap.put("name", "apple phone");
        phoneMap.put("money", "5000");
        return phoneMap;
    }
}
