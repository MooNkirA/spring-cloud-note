package com.moon.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Http请求相关的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-8 11:35
 * @description
 */
@Configuration
public class HttpConfig {

    /**
     * 创建RestTemplate对象注册到Spring容器管理
     *
     * @return
     */
    @LoadBalanced // 是Ribbon组件提供的负载均衡的注解，声明此注解后就可以基于Ribbon的服务调用与负载均衡
    @Bean("restTemplate")
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

}
