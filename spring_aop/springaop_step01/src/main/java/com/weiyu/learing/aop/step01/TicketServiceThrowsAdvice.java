package com.weiyu.learing.aop.step01;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * 抛出异常时的处理意见
 * @author: weiyu
 * @date: 2018/2/2
 */
public class TicketServiceThrowsAdvice implements ThrowsAdvice {


    /**
     * 对未知异常的处理.
     * @param method
     * @param args
     * @param target
     * @param ex
     */
    public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
        System.out.println("调用过程出错啦！！！！！");
    }

    /**
     * 对NullPointerException异常的处理
     * @param method
     * @param args
     * @param target
     * @param ex
     * @throws Throwable
     */
    public void afterThrowing(Method method, Object[] args, Object target,
                              NullPointerException ex) throws Throwable {
        System.out.println("*************************************");
        System.out.println("Error happened in class: " + target.getClass().getName());
        System.out.println("Error happened in method: " + method.getName());

        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "]: " + args[i]);
        }

        System.out.println("Exception class: " + ex.getClass().getName());
        System.out.println("*************************************");
    }
}
