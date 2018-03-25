package com.weiyu.learning.ribbon.client;

import com.google.common.collect.Lists;
import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.loadbalancer.*;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: weiyu
 * @date: 2018/3/5
 */
public class BestAvailableRuleApp {
    private static final Logger logger = LoggerFactory.getLogger(BestAvailableRuleApp.class);


    private final ILoadBalancer loadBalancer;
    // retry handler that does not retry on same server, but on a different server
    private final RetryHandler retryHandler = new DefaultLoadBalancerRetryHandler(0, 1, true);


    public BestAvailableRuleApp(List<Server> serverList) {

        //最少并发数策略
        loadBalancer = LoadBalancerBuilder.newBuilder().withRule(
                new BestAvailableRule()
        ).buildFixedServerListLoadBalancer(serverList);
    }


    public String call(final String path) throws Exception {
        return LoadBalancerCommand.<String>builder()
                .withLoadBalancer(loadBalancer)
                .build()
                .submit(new ServerOperation<String>() {
                    @Override
                    public Observable<String> call(Server server) {
                        URL url;
                        try {
                            System.out.println(String.format("%s:%s",server.getHost(),server.getPort()));

                            url = new URL("http://" + server.getHost() + ":" + server.getPort() + path);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            return Observable.just(conn.getResponseMessage());
                        } catch (Exception e) {
                            return Observable.error(e);
                        }
                    }
                }).toBlocking().first();
    }

    public LoadBalancerStats getLoadBalancerStats() {
        return ((BaseLoadBalancer) loadBalancer).getLoadBalancerStats();
    }

    public static void main(String[] args) throws Exception {
        BestAvailableRuleApp urlLoadBalancer = new BestAvailableRuleApp(Lists.newArrayList(
                new Server("www.ctrip.com", 80),
                new Server("www.163.com", 80),
                new Server("www.baidu.com", 80),
                new Server("www.taobao.com", 80),
                new Server("www.jd.com", 80)
        ));

        /**
         如果使用单线程的话，每次访问完成后，其请求数变成0，所以永远是第一个。
        for (int i = 0; i < 20; i++) {
            System.out.println(urlLoadBalancer.call("/"));
        }
         */

        //模拟多线程访问
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(urlLoadBalancer.call("/"));
                        Thread.sleep(500+new Random().nextInt(500));
                    }catch (Exception e){
                        logger.error(e.getMessage());
                    }
                }
            });

        }



        System.out.println("=== Load balancer stats ===");
        System.out.println(urlLoadBalancer.getLoadBalancerStats());
        Thread.sleep(10000);
        System.exit(0);
    }
}
