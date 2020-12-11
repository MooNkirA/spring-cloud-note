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
@EnableEurekaServer // 标识开启Eureka Server端
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
