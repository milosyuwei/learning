package com.weiyu.learning.ribbon.client.rule;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.weiyu.learning.ribbon.client.utils.ConsistencyHash;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: weiyu
 * @date: 2018/3/5
 */
public class ConsistenHashRule extends AbstractLoadBalancerRule {

    private static final Logger logger = LoggerFactory.getLogger(ConsistenHashRule.class);

    private AtomicInteger nextServerCyclicCounter;

    public ConsistenHashRule(){
        this.nextServerCyclicCounter = new AtomicInteger(0);
    }

    public Server choose(ILoadBalancer lb, Object key) {

        if (lb == null) {
            logger.warn("no load balancer");
            return null;
        } else {
            Server server = null;


            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();

            int upCount = reachableServers.size();
            int serverCount = allServers.size();
            if (upCount != 0 && serverCount != 0) {

                int nextServerIndex = this.hashAndGetModulo(key, serverCount);
                server = allServers.get(nextServerIndex);

                if (server.isAlive() && server.isReadyToServe()) {
                    return server;
                }else{
                    return choiseByRound(lb);
                }
            }
            logger.warn("No up servers available from load balancer: " + lb);
            return null;
        }
    }

    /**
     * 使用RoundRobinRule算法
     * @param lb
     * @return
     */
    private Server choiseByRound(ILoadBalancer lb){
        Server server = null;
        int count = 0;
        while(true) {
            if (server == null && count++ < 10) {
                List<Server> reachableServers = lb.getReachableServers();
                List<Server> allServers = lb.getAllServers();
                int upCount = reachableServers.size();
                int serverCount = allServers.size();
                if (upCount != 0 && serverCount != 0) {
                    int nextServerIndex = this.incrementAndGetModulo(serverCount);
                    server = (Server)allServers.get(nextServerIndex);
                    if (server == null) {
                        Thread.yield();
                    } else {
                        if (server.isAlive() && server.isReadyToServe()) {
                            return server;
                        }

                        server = null;
                    }
                    continue;
                }

                logger.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            if (count >= 10) {
                logger.warn("No available alive servers after 10 tries from load balancer: " + lb);
            }

            return server;
        }
    }


    private int hashAndGetModulo(Object key, int serverCount) {
        try {
            if (key == null) {
                return incrementAndGetModulo(serverCount);
            } else {
                Gson gson = new Gson();
                String jsonKey = gson.toJson(key);
                if (StringUtils.isBlank(jsonKey))
                    return incrementAndGetModulo(serverCount);
                else {
                	//hashcode 与  0x7FFFFFFF 进行 与 运算 保证是一个正数
                	int index = (hash(jsonKey) & 0x7FFFFFFF) % serverCount;
                    return index ;
                }
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return incrementAndGetModulo(serverCount);
        }
    }

    private int incrementAndGetModulo(int modulo) {
        int current;
        int next;
        do {
            current = this.nextServerCyclicCounter.get();
            next = (current + 1) % modulo;
        } while(!this.nextServerCyclicCounter.compareAndSet(current, next));

        return next;
    }

    private Integer hash(String key) {
        try {
            return DigestUtils.md5DigestAsHex(key.getBytes()).hashCode();
        }catch (Exception e){
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return 0;
    }
    /*
    private Set<String> getServerSet(List<Server> servers) {
        Set<String> tmp = Sets.newConcurrentHashSet();
        for(Server server:servers){
            tmp.add(server.getHostPort());
        }
        return tmp;
    }*/

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
