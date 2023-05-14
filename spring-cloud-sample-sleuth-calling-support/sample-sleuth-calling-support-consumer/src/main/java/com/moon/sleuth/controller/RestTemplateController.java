package com.moon.sleuth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 测试 RestTemplate 调用方式
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-08 22:46
 * @description
 */
@Slf4j
@RestController
public class RestTemplateController {

    // 引入 RestTemplate
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/testByRestTemplate")
    public String testByRestTemplate() {
        // 使用 RestTemplate 服务调用
        String result = restTemplate.getForObject("http://localhost:8081/hello", String.class);
        // 输出日志
        log.info("testByRestTemplate result: {}", result);
        return result;
    }

}
