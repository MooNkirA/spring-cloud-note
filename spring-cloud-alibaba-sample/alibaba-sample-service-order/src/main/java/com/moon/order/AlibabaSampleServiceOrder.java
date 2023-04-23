package com.moon.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 项目启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:25
 * @description
 */
@SpringBootApplication
// Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能
@EnableDiscoveryClient
// 开启fegin的客户端
@EnableFeignClients
public class AlibabaSampleServiceOrder {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaSampleServiceOrder.class, args);
    }

}