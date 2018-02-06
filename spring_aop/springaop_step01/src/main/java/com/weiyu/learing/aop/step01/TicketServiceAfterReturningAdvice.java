package com.weiyu.learing.aop.step01;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * 返回结果时后的处理意见
 * @author: weiyu
 * @date: 2018/2/2
 */
public class TicketServiceAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("AFTER_RETURNING：本次服务已结束....");
    }
}
