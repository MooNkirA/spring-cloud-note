package com.moon.example.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 服务提供者
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-21 23:11
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FeignNacosProviderApp {

    public static void main(String[] args) {
        SpringApplication.run(FeignNacosProviderApp.class, args);
    }

}
