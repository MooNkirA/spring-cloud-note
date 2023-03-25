package com.moon.example.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-24 23:15
 * @description
 */
@RestController
public class ComsumerTestController {

    @Autowired
    private FeignClientDemo feignClientDemo;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return feignClientDemo.hello(name);
    }

}
