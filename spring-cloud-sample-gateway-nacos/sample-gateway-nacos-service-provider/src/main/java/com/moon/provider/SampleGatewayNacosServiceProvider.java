package com.moon.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-01 22:25
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SampleGatewayNacosServiceProvider {

    public static void main(String[] args) {
        SpringApplication.run(SampleGatewayNacosServiceProvider.class, args);
    }

}
