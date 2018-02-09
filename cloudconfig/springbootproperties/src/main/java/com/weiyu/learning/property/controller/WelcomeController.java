package com.weiyu.learning.property.controller;

import com.weiyu.learning.property.myconfig.ListDefineConfig;
import com.weiyu.learning.property.myconfig.RemoteProperties;
import com.weiyu.learning.property.myconfig.MapDefineConfig;

import com.weiyu.learning.property.myconfig.ValueDefineConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weiyu on 2018/1/27.
 */

@RestController
public class WelcomeController {
    //@Value("${foo}")
    //String foo;
    @Autowired
    RemoteProperties remoteProperties;
    @Autowired
    MapDefineConfig mapDefineConfig;

    @Autowired
    ListDefineConfig listDefineConfig;

    @Autowired
    ValueDefineConfig valueDefineConfig;

    @RequestMapping(value = "index")
    public String index(){
        System.out.println(remoteProperties.getUploadFilesUrl());
        System.out.println(mapDefineConfig.getImage());
        System.out.println(listDefineConfig.getServers());
        System.out.println(valueDefineConfig.getNum());
        System.out.println(valueDefineConfig.getNames());
        return remoteProperties.getUploadFilesUrl();
    }
}
