package com.moon.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Gateway 工程启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-05 8:43
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaSampleApiGateway {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaSampleApiGateway.class, args);
    }

}
