package com.weiyu.learning.hystrix.controller;

import com.netflix.hystrix.HystrixCommand;
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
    public String index() {
        /*
       String rtn = new HystrixCommand<String>(){

            @Override
            protected String run() throws Exception {
                return null;
            }
        }.run();*/
        return "";
    }
}
