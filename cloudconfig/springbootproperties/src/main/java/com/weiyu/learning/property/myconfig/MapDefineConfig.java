package com.weiyu.learning.property.myconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: weiyu
 * @date: 2018/2/9
 */
@Configuration   //表明这是一个配置类
@PropertySource("classpath:maps.properties")  //配置文件路径
//该注解用于绑定属性 读取前缀为 upload 的内容
@ConfigurationProperties(prefix = "upload")  //使用 spring-boot-configuration-processor jar
@Component
public class MapDefineConfig {
    private Map image;
    private Map office;
    private Map text;

    public Map getImage() {
        return image;
    }

    public void setImage(Map image) {
        this.image = image;
    }

    public Map getOffice() {
        return office;
    }

    public void setOffice(Map office) {
        this.office = office;
    }

    public Map getText() {
        return text;
    }

    public void setText(Map text) {
        this.text = text;
    }
}
