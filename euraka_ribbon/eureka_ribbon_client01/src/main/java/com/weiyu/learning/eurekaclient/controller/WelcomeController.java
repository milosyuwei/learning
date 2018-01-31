package com.weiyu.learning.eurekaclient.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by weiyu on 2018/1/19.
 */
@RestController
public class WelcomeController {
    @Resource
    private DiscoveryClient client;

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "retryFailback")  //熔断降级机制
    @RequestMapping(value = "index")
    public String index() {

        /*
        ServiceInstance instance = client.getLocalServiceInstance();
        //根据名字获取 服务实例 List<ServiceInstance>
        List<ServiceInstance> instanceList = client.getInstances("HELLO-CLIENT");
        return String.format("uri=%s，serviceId=%s，result=%s", instance.getUri(), instance.getServiceId(), "Hello World!");
        */
        //eureka-server-ha
        //会自动根据 EUREKA-SERVER-HA 服务名，进行ribbon 路由
        //其 restTemplate 需要有 @LoadBalanced //开启负债均衡的能力 修饰
        String rtn = restTemplate.getForEntity("http://SERVER-PROVIDER/calculator/add?a=1&b=2", String.class).getBody();
        return rtn;
    }




    public String gethystrix(){
        String rtn = restTemplate.getForEntity("http://SERVER-PROVIDER/calculator/add?a=1&b=2", String.class).getBody();
        return rtn;
    }
    /**
     * 熔断重试
     *
     * @return
     */
    public String retryFailback() {
        /*
        String rtn = restTemplate.getForEntity("http://SERVER-PROVIDER/calculator/add?a=1&b=2",String.class).getBody();
        return rtn;
        */
        return "HystrixCommand";
    }
}
