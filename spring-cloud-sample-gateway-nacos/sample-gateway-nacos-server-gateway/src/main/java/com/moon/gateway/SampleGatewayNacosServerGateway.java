package com.moon.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-02 07:57
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SampleGatewayNacosServerGateway {

    public static void main(String[] args) {
        SpringApplication.run(SampleGatewayNacosServerGateway.class, args);
    }

}
