package com.moon.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 项目启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 17:21
 * @description
 */
@SpringBootApplication
// Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能
@EnableDiscoveryClient
public class AlibabaSampleServiceProduct {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaSampleServiceProduct.class, args);
    }

}
