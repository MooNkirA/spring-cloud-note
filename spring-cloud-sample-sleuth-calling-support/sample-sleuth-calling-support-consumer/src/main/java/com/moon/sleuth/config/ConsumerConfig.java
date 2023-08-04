package com.moon.sleuth.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 项目配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-08 22:44
 * @description
 */
@Configuration
public class ConsumerConfig {

    // 创建 RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private BeanFactory beanFactory;

    // 定义可跟踪的线程服务
    @Bean
    public ExecutorService executorService(){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        return new TraceableExecutorService(this.beanFactory, executorService);
    }
}
