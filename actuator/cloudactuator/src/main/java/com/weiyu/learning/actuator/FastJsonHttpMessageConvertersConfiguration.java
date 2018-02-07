package com.weiyu.learning.actuator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


/**
 * Spring MVC 解决 Could not write JSON: No serializer found for class java.lang.Object
 * 自己制定 JSON 序列化 对象。
 * 解决　http://localhost:31011/admin/showEndpoints　是的错误
 * Could not write JSON: No serializer found for class org.springframework.core.convert.support.DefaultConversionService and no properties discovered to create BeanSerializer
 */
@Configuration
public class FastJsonHttpMessageConvertersConfiguration {
    @Bean
    public org.springframework.http.converter.json.MappingJackson2HttpMessageConverter fastJsonHttpMessageConverters(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
