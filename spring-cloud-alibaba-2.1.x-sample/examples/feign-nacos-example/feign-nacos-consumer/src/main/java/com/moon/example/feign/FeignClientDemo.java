package com.moon.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FeignClient 接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-23 22:47
 * @description
 */
// @FeignClient 注解，用于标识当前接口为Feign调用微服务的核心接口
@FeignClient(name = "service-provider")
public interface FeignClientDemo {

    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);

}
