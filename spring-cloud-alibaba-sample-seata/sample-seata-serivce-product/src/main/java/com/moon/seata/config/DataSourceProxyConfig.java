package com.moon.seata.config;

import com.alibaba.druid.pool.DruidDataSource;
// import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Seata 通过代理数据源的方式实现分支事务；MyBatis 和 JPA 都需要注入 io.seata.rm.datasource.DataSourceProxy,
 * 不同的是，MyBatis 还需要额外注入 org.apache.ibatis.session.SqlSessionFactory
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-09 16:07
 * @description
 */
@Configuration
// seata-spring-boot-starter 1.1.0 版本后，不能手动创建 DataSourceProxy。需要通过此注解开启数据源自动代理
// @EnableAutoDataSourceProxy
public class DataSourceProxyConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean
    public DataSourceProxy dataSource(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

}
