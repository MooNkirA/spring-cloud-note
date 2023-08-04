package com.moon.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * 商品微服务启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-8 09:39
 * @description
 */
@SpringBootApplication(scanBasePackages = "com.moon.product")
@EntityScan("com.moon.entity") // 指定扫描实体类的包路径
/* 从Spring Cloud Edgware版本开始，以下两个注解均可以省略不写。只需加上相关依赖与相应配置，即可注册服务 */
// @EnableEurekaClient // 基于spring-cloud-netflix依赖，只能为eureka作用
// @EnableDiscoveryClient // 基于spring cloud自身的依赖，可以作用于除了eureka之外的注册中心
public class SampleRibbonServiceProduct {

    public static void main(String[] args) {
        SpringApplication.run(SampleRibbonServiceProduct.class, args);
    }

}
