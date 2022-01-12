package com.moon.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate 请求配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-01 12:20
 * @description
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // @LoadBalanced 注解让 RestTemplate 接入 Ribbon 实现负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
