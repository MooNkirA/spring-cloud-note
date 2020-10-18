package com.moon.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * 订单微服务启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-8 09:46
 * @description
 */
@EntityScan("com.moon.entity") // 指定扫描实体类的包路径
// @SpringBootApplication(scanBasePackages = "com.moon.order")
// @EnableCircuitBreaker // 开启hystrix熔断支持
@SpringCloudApplication // 此组合注解相当于 @SpringBootApplication + @EnableDiscoveryClient + @EnableCircuitBreaker
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
