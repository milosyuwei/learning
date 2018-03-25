//package com.weiyu.learning.task.dao;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
///**
// * @author: weiyu
// * @date: 2018/3/8
// */
//@Configuration
//public class DataSourceConfiguration {
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource primaryDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    /*
//    @Bean(name = "secondDatasource")
//    @ConfigurationProperties(prefix = "spring.second-datasource")
//    public DataSource secondDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//    */
//
//
//    @Bean
//    @Primary
//    public JdbcTemplate primaryJdbcTemplate(DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//
//    /*
//    @Bean(name = "secondJdbcTemplate")
//    public JdbcTemplate secondJdbcTemplate(@Qualifier("secondDatasource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//    */
//
//
//}
