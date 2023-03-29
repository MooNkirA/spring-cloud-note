package com.moon.example.feign;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * FeignClient 配置类，用于局部日志配置级别
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-25 23:50
 * @description
 */
public class FeignClientConfig {

    // 配置日志级别
    @Bean
    public Logger.Level level() {
        return Logger.Level.FULL;
    }

}
