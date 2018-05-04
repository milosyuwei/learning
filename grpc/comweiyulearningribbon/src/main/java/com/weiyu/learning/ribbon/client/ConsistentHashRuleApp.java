package com.weiyu.learning.ribbon.client;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.netflix.client.DefaultLoadBalancerRetryHandler;
import com.netflix.client.RetryHandler;
import com.netflix.loadbalancer.*;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;
import com.weiyu.learning.ribbon.client.rule.ConsistenHashRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: weiyu
 * @date: 2018/3/5
 */
public class ConsistentHashRuleApp {

	private static final Logger logger = LoggerFactory.getLogger(BestAvailableRuleApp.class);

	private final ILoadBalancer loadBalancer;
	// retry handler that does not retry on same server, but on a different server
	private final RetryHandler retryHandler = new DefaultLoadBalancerRetryHandler(0, 1, true);

	public ConsistentHashRuleApp(List<Server> serverList) {

		// Hash策略
		loadBalancer = LoadBalancerBuilder.newBuilder().withRule(new ConsistenHashRule())
				.buildFixedServerListLoadBalancer(serverList);
		
	}

	public String call(final String path, final String key) throws Exception {
		return LoadBalancerCommand.<String>builder().withLoadBalancer(loadBalancer).withServerLocator(key)
				.withRetryHandler(retryHandler).build().submit(new ServerOperation<String>() {
					@Override
					public Observable<String> call(Server server) {
						URL url;
						try {
							System.out.println(String.format("%s:%s", server.getHost(), server.getPort()));

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
		Map<String,Server> serversMap = Maps.newHashMap();
		
		serversMap.put("ctrip", new Server("www.ctrip.com", 80));
		serversMap.put("163", new Server("www.163.com", 80));
		serversMap.put("baidu", new Server("www.baidu.com", 80));
		serversMap.put("taobao", new Server("www.taobao.com", 80));
		serversMap.put("jd", new Server("www.jd.com", 80));
		

		ConsistentHashRuleApp urlLoadBalancer = new ConsistentHashRuleApp(Lists.newArrayList(serversMap.values()));

		for (int i = 0; i < 20; i++) {
			System.out.println(urlLoadBalancer.call("/", i + ""));
		}

		System.out.println("=== Load balancer stats ===");
		System.out.println(urlLoadBalancer.getLoadBalancerStats());
		Thread.sleep(10000);
		
		
		System.exit(0);
	}
}
