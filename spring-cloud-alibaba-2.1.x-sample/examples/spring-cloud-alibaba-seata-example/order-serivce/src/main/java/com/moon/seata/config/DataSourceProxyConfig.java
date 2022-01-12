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

    /*
       seata 变更记录
        20200716(1.3.0):
        1.增加了store.redis相关配置
        2.增加了nacos注册中心配置group项,Server和Client端的值需一致
        20200421(1.2.0):
        1.增加registry.nacos.application属性，默认seata-server，Server和Client端的值需一致
        20200220(1.1.0):
        1.file.conf和registry.conf两个配置文件中的格式统一转换为驼峰格式.
        2.统一所有配置文件的默认值(file.conf、registry.conf、seata-spring-boot-starter)
        3.优化seata-spring-boot-starter中对于事务分组和TC集群的配置
        4.移除client.support.spring.datasource.autoproxy,增加@EnableAutoDataSourceProxy
        注解用于开启数据源自动代理,同时可选择代理实现方式(具体请查阅附录5)
        20191221:
        1.增加seata.enabled、client.report.success.enable、
        transport.enable-client-batch-send-request、client.log.exceptionRate
    */
    @Primary
    @Bean
    public DataSourceProxy dataSource(DruidDataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

}
