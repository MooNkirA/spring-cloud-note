package com.moon.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-08 22:39
 * @description
 */
@EnableFeignClients
@SpringBootApplication
public class SampleSleuthCallingSupportConsumer {

    public static void main(String[] args) {
        SpringApplication.run(SampleSleuthCallingSupportConsumer.class, args);
    }

}
