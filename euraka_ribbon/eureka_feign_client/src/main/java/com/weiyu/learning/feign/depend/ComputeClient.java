package com.weiyu.learning.feign.depend;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by weiyu on 2018/1/22.
 */
//绑定該接口到CalculatorServer服务，并通知Feign组件对该接口进行代理（不需要编写接口实现）
//可以添加 fallback指定在失败回调方法  ，赞时没有成功
@FeignClient(value = "SERVER-PROVIDER",fallbackFactory = ComputeClient.HystrixClientFallbackFactory.class)
public interface ComputeClient {
    @RequestMapping(method = RequestMethod.GET, value = "/calculator/add")
    String add(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b);

    /**
     * 这里采用和SpringCloud官方文档相同的做法，把fallback类作为内部类放入Feign接口中
     * http://cloud.spring.io/spring-cloud-static/Camden.SR6/#spring-cloud-feign-hystrix
     * （也可以外面独立定义该类，个人觉得没必要，这种东西写成内部类最合适）
     */
    @Component
    class HystrixClientFallbackFactory implements FallbackFactory<ComputeClient> {
        @Override
        public ComputeClient create(Throwable cause) {
            return new ComputeClient() {
                @Override
                public String add(@RequestParam("a") int a, @RequestParam("b") int b){
                    return "Hystrix";
                }
            };
        }
    }
}

