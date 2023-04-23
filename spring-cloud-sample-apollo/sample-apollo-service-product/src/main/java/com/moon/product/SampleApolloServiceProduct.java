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
public class SampleApolloServiceProduct {

    public static void main(String[] args) {
        SpringApplication.run(SampleApolloServiceProduct.class, args);
    }

}
