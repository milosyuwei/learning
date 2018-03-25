package com.weiyu.learning.ribbon.client;

import com.google.common.collect.Lists;
import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.loadbalancer.*;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import rx.Observable;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author: weiyu
 * @date: 2018/3/5
 */
public class RandomRuleApp {

    private final ILoadBalancer loadBalancer;
    // retry handler that does not retry on same server, but on a different server
    private final RetryHandler retryHandler = new DefaultLoadBalancerRetryHandler(0, 1, true);


    public RandomRuleApp(List<Server> serverList) {

        //随机选择
        loadBalancer = LoadBalancerBuilder.newBuilder().withRule(
                new RandomRule()
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
        RandomRuleApp urlLoadBalancer = new RandomRuleApp(Lists.newArrayList(
                new Server("www.ctrip.com", 80),
                new Server("www.163.com", 80),
                new Server("www.baidu.com", 80),
                new Server("www.taobao.com", 80),
                new Server("www.jd.com", 80)
        ));

        for (int i = 0; i < 20; i++) {
            System.out.println(urlLoadBalancer.call("/"));
        }




        System.out.println("=== Load balancer stats ===");
        System.out.println(urlLoadBalancer.getLoadBalancerStats());
        System.exit(0);
    }
}
