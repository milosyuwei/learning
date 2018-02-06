package com.weiyu.learing.aop.step02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: weiyu
 * @date: 2018/2/5
 */
@RestController
@SpringBootApplication
public class App {

    @Autowired
    Chinese chinese;

    @RequestMapping("/test")
    public void test() {
        chinese.sayHello("listen");
        System.out.println(chinese.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
