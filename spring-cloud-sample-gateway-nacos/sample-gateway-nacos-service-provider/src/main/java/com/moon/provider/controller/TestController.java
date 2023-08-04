package com.moon.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 测试接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-01 22:25
 * @description
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hello " + name + "!";
    }

    @GetMapping("/test/head")
    public String testGatewayHead(HttpServletRequest request, HttpServletResponse response) {
        String head = request.getHeader("X-Request-red");
        return "X-Request-red : " + head;
    }

    @GetMapping("/test/param")
    public String testGatewayParam(HttpServletRequest request, HttpServletResponse response) {
        String val = request.getParameter("red");
        return "param red : " + val;
    }

}
