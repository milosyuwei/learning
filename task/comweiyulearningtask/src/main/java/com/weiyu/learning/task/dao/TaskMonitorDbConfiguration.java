package com.weiyu.learning.task.dao;

import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * @author: weiyu
 * @date: 2018/3/10
 */
@Configuration
@MapperScan(basePackages = "com.weiyu.learning.task.dao.taskmanagerdb.mapper", sqlSessionFactoryRef="taskMonitorDbSqlSessionFactory")
public class TaskMonitorDbConfiguration {
    @Bean(name = "taskMonitorDbds")
    @ConfigurationProperties(prefix = "spring.datasource.taskMonitorDb")
    @Primary
    public DataSource taskMonitorDbDataSource() {
        //DataSourceBuilder.create().password("").username("").url("").driverClassName("com.mysql.jdbc.Driver").build();
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "taskMonitorDbSqlSessionFactory")
    @Primary
    public SqlSessionFactory taskMonitorDbSqlSessionFactory(@Qualifier("taskMonitorDbds") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(
                new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config-taskdb.xml") );
        return bean.getObject();
    }

    @Bean(name = "taskMonitorDbTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("taskMonitorDbds") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name = "taskMonitorDbSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("taskMonitorDbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Primary
    public JdbcTemplate taskMonitorDbJdbcTemplate(@Qualifier("taskMonitorDbds") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
