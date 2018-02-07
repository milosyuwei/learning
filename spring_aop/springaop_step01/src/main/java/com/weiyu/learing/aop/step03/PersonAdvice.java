package com.weiyu.learing.aop.step03;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: weiyu
 * @date: 2018/2/5
 */
@Aspect
@Component
public class PersonAdvice {

    /**
     * 可以通过 @Around 来指定
     */

    @Around(value = "execution(* com.weiyu.learing.aop.step03.Person.*(*))")
    public void myPointcut(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println("before Around");
        pjp.proceed();
        System.out.println("after Around");
    }


    /*
    使用 Before 需要先 poitcut一个方法，然后再 使用 @Before
    */
    @Before(value="execution(* com.weiyu.learing.aop.step03.Person.*(*))")
    public void before() {
        System.out.println("before");
    }

    @After(value="execution(* com.weiyu.learing.aop.step03.Person.*(*))")
    public void after(){
        System.out.println("after");
    }
}
