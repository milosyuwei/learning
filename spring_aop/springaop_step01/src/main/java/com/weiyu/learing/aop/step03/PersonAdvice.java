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
     * 使用JoinPoint获取：Spring AOP提供使用org.aspectj.lang.JoinPoint类型获取连接点数据，
     * 任何通知方法的第一个参数都可以是JoinPoint(环绕通知是ProceedingJoinPoint，JoinPoint子类)，
     * 当然第一个参数位置也可以是JoinPoint.StaticPart类型，这个只返回连接点的静态部分。
     */
/*
JoinPoint定义
public interface JoinPoint {
    String toString();         //连接点所在位置的相关信息
    String toShortString();     //连接点所在位置的简短相关信息
    String toLongString();     //连接点所在位置的全部相关信息
    Object getThis();         //返回AOP代理对象
    Object getTarget();       //返回目标对象
    Object[] getArgs();       //返回被通知方法参数列表
    Signature getSignature();  //返回当前连接点签名
    SourceLocation getSourceLocation();//返回连接点方法所在类文件中的位置
    String getKind();        //连接点类型
    StaticPart getStaticPart(); //返回连接点静态部分
}
ProceedingJoinPoint：用于环绕通知，使用proceed()方法来执行目标方法：
public interface ProceedingJoinPoint extends JoinPoint {
    public Object proceed() throws Throwable;
    public Object proceed(Object[] args) throws Throwable;
}
3) JoinPoint.StaticPart：提供访问连接点的静态部分，如被通知方法签名、连接点类型等：
public interface StaticPart {
Signature getSignature();    //返回当前连接点签名
String getKind();          //连接点类型
    int getId();               //唯一标识
String toString();         //连接点所在位置的相关信息
    String toShortString();     //连接点所在位置的简短相关信息
    String toLongString();     //连接点所在位置的全部相关信息
}

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
