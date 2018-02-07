package com.weiyu.learing.aop.step03;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: weiyu
 * @date: 2018/2/6
 * 利用 @Aspect 和 @AfterThrowing 可以在Spring boot中使用
 */
@Aspect
@Component
public class ThrowExceptionAop {
    private static final Logger logger = LoggerFactory.getLogger(ThrowExceptionAop.class);
    //JoinPoint joinPoint 参数可以省略
    @AfterThrowing(throwing="ex",pointcut="execution(* com.weiyu.learing.aop.step03.*.*(..))")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex){

        System.out.println("Okay - we're in the handler...");

        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String stuff = signature.toString();
        String arguments = Arrays.toString(joinPoint.getArgs());
        logger.info("Write something in the log... We have caught exception in method: "
                + methodName + " with arguments "
                + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
                + ex.getMessage(), ex);

        System.out.println("调用过程出错啦！！！！！");
        ex.printStackTrace();
    }
}

