package com.moon.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka服务端启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-9 10:20
 * @description
 */
@SpringBootApplication
// 标识开启Eureka Server端
@EnableEurekaServer
public class SampleGatewayServerEureka {

    public static void main(String[] args) {
        SpringApplication.run(SampleGatewayServerEureka.class, args);
    }

}
