package com.moon.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Config Server 启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-28 10:58
 * @description
 */
@SpringBootApplication
@EnableConfigServer // 开启注册中心服务端功能
public class SampleConfigServerConfig {

    public static void main(String[] args) {
        SpringApplication.run(SampleConfigServerConfig.class, args);
    }

}
