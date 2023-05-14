package com.moon.sleuth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Feign 调用代理接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-08 23:04
 * @description
 */
@FeignClient(name = "sleuth-provider", url = "localhost:8081")
public interface HelloService {

    @RequestMapping("/hello")
    String hello();

}
