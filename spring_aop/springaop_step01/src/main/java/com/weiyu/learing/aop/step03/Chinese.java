package com.weiyu.learing.aop.step03;

import org.springframework.stereotype.Component;

/**
 * @author: weiyu
 * @date: 2018/2/5
 */
@Component
public class Chinese implements Person {
    @Override
    public String sayHello(String name) {
        System.out.println("-- sayHello() --" + name);
        return name + " hello, AOP";
    }

    @Override
    public String sayError(String name) throws Exception {
        throw new Exception("just test throws advice");
    }


}
