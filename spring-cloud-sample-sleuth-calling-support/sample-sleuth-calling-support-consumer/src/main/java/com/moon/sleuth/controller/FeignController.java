package com.moon.sleuth.controller;

import com.moon.sleuth.feign.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 Feign 调用方式
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-08 23:07
 * @description
 */
@Slf4j
@RestController
public class FeignController {
    // 引入 FeignClient 接口
    @Autowired
    HelloService helloService;

    @GetMapping("/testByFeign")
    public String testByFeign() {
        // Feign 服务调用
        String result = helloService.hello();
        // 输出日志
        log.info("testByFeign result: {}", result);
        return result;
    }

}
