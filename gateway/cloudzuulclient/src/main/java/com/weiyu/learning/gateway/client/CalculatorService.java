package com.weiyu.learning.gateway.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by weiyu on 2018/1/25.
 */
//绑定該接口到服务网关的jadyer-api-gateway服务，并通知Feign组件对该接口进行代理（不需要编写接口实现）
@FeignClient(value="ZUUL-API-GATEWAY", fallback=CalculatorService.HystrixCalculatorService.class)
public interface CalculatorService {
    @RequestMapping(value="/mygateway/calculator/add", method= RequestMethod.GET)
    String myadd(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("accesstoken") String accesstoken);

    @Component
    class HystrixCalculatorService implements CalculatorService {
        @Override
        public String myadd(@RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("accesstoken") String accesstoken) {
            return "Hystrix";
        }
    }
}
