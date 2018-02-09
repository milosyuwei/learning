package com.weiyu.learning.property.myconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: weiyu
 * @date: 2018/2/9
 */
@Configuration   //表明这是一个配置类
@PropertySource("classpath:list.properties")  //配置文件路径
//该注解用于绑定属性 读取前缀为 upload 的内容 ，目前只能使用 Object[] ，暂时List类型暂时没有成功
@ConfigurationProperties(prefix = "list")  //使用 spring-boot-configuration-processor jar
@Component
public class ListDefineConfig {

    //
    private String[] servers;

    public String[] getServers() {
        return servers;
    }

    public void setServers(String[] servers) {
        this.servers = servers;
    }
}
