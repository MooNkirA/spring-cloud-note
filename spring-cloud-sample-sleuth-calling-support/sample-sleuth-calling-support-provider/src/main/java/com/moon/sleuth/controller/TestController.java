package com.moon.sleuth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-08 22:34
 * @description
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        log.info("service provider");
        return "hello !";
    }

}

