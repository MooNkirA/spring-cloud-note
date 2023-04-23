package com.moon.example.feign;

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;
import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-29 22:36
 * @description
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class OkHttpConfiguration {

    /* 配置 OkHttp Client */
    @Bean
    public OkHttpClient okHttpClient() {
        // 设置相关配置项
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS) // 1.连接超时时间
                .readTimeout(20, TimeUnit.SECONDS) // 2.响应超时时间
                .writeTimeout(20, TimeUnit.SECONDS) // 3.写超时时间
                .retryOnConnectionFailure(true) // 4.自动重连
                .connectionPool(new ConnectionPool()) // 5.配置连接池
                .addInterceptor(new LogInterceptor()) // 6.添加日志拦截器
                .build();
    }

}
