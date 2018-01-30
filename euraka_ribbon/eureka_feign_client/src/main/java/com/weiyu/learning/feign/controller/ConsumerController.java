package com.weiyu.learning.feign.controller;

import com.weiyu.learning.feign.depend.ComputeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weiyu on 2018/1/22.
 */
@RestController
public class ConsumerController {
    @Autowired
    private ComputeClient computeClient;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return computeClient.add(10, 20);
    }

}
