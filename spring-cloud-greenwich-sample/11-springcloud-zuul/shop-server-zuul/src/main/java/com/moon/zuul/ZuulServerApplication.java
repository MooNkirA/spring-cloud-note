package com.moon.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul 网关服务启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-23 11:20
 * @description
 */
@SpringBootApplication
@EnableZuulProxy // 开启zuul网关功能
@EnableDiscoveryClient // 开启服务发现功能。从Spring Cloud Edgware版本开始，以下两个注解均可以省略不写。只需加上相关依赖与相应配置，即可注册服务
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

}
