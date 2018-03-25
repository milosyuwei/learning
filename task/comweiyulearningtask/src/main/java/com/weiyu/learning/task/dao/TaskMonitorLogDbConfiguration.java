package com.weiyu.learning.task.dao;

import org.apache.ibatis.session.SqlSessionFactory;
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

/**
 * @author: weiyu
 * @date: 2018/3/10
 */
@Configuration
@MapperScan(basePackages = "com.weiyu.learning.task.dao.taskmanagerlogdb.mapper", sqlSessionFactoryRef="taskMonitorLogDbSqlSessionFactory")
public class TaskMonitorLogDbConfiguration {
    @Bean(name = "taskMonitorLogDbds")
    @ConfigurationProperties(prefix = "spring.datasource.taskmonitorLogDb")
    public DataSource taskMonitorLogDbds() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "taskMonitorLogDbSqlSessionFactory")
    public SqlSessionFactory taskMonitorLogDbSqlSessionFactory(@Qualifier("taskMonitorLogDbds") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(
                new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config-tasklogdb.xml") );
        return bean.getObject();
    }

    @Bean(name = "taskMonitorLogDbTransactionManager")
    public DataSourceTransactionManager taskMonitorLogDbTransactionManager(@Qualifier("taskMonitorLogDbds") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean(name = "taskMonitorLogDbSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("taskMonitorLogDbSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean(name = "taskMonitorLogDbJdbcTemplate")
    public JdbcTemplate taskMonitorLogDbJdbcTemplate(@Qualifier("taskMonitorLogDbds") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
