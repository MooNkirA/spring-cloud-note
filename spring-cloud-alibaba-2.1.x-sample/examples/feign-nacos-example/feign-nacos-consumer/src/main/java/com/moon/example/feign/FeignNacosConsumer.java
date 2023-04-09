package com.moon.example.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 服务消费者
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-21 23:42
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
// defaultConfiguration 属性指定全局配置
// @EnableFeignClients(defaultConfiguration = FeignClientConfig.class)
public class FeignNacosConsumer {

    public static void main(String[] args) {
        SpringApplication.run(FeignNacosConsumer.class, args);
    }

}
