package com.weiyu.learning.task.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * {@code Spring}容器获取工厂
 *
 * <p>这里使用注解方法，把工具自动注入容器中，主要是为了获取{@code Spring}容器对象</p>
 *
 * @author weiyu
 */
@Component
public final class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static CountDownLatch latch = new CountDownLatch(1);

    /**
     * 不允许实例化
     */
    private SpringContextUtil() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.applicationContext != null) {
            return;
        }

        synchronized (SpringContextUtil.class) {
            if (SpringContextUtil.applicationContext == null) {
                SpringContextUtil.applicationContext = applicationContext;
                latch.countDown();
            }
        }
    }

    /**
     * 获取{@code Spring}容器信息
     *
     * @return 返回{@code Spring}容器信息
     */
    public static ApplicationContext getApplicationContext() {
        if (applicationContext != null) {
            return applicationContext;
        }

        try {
            if (latch.await(60, TimeUnit.SECONDS)) {
                return applicationContext;
            }
            throw new RuntimeException("Waited for applicationContext to inject, but timed out (60s).");
        } catch (InterruptedException iex) {
            throw new RuntimeException(iex);
        }
    }

    /**
     * 根据对象类型获取容器中对象
     *
     * @param clazz 查找对象类型
     * @return 返回容器中注册的对象
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 根据检索名称和对象类型获取容器中对象
     *
     * @param clazz 查找对象类型
     * @param name  要检索的名称
     * @return 返回容器中注册的对象
     */
    public static <T> T getBean(Class<T> clazz, String name) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 把逻辑地址转换成物理地址
     *
     * @param location 逻辑地址
     * @return 物理地址
     */
    public static Resource getResource(String location) {
        return getApplicationContext().getResource(location);
    }
}