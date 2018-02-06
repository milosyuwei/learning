package com.weiyu.learing.aop.step02;

import org.springframework.stereotype.Component;

/**
 * @author: weiyu
 * @date: 2018/2/5
 */
@Component
public class Chinese implements Person{
    @Override
    public String sayHello(String name) {
        System.out.println("-- sayHello() --");
        return name + " hello, AOP";
    }
}
