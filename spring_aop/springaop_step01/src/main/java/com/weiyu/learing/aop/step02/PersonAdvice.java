package com.weiyu.learing.aop.step02;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: weiyu
 * @date: 2018/2/5
 */
@Aspect
@Component
public class PersonAdvice {

    @Pointcut(value = "execution(* com.weiyu.learing.aop.step02.Person.*(*))")
    public void myPointcut(){
        System.out.println("myPointcut");
    }

    @Before("myPointcut()")
    public void before() {
        System.out.println("before");
    }
}
