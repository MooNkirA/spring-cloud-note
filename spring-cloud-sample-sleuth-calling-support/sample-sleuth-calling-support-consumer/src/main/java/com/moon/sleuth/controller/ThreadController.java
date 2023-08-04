package com.moon.sleuth.controller;

import com.moon.sleuth.feign.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 多线程调用方式跟踪测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-10 21:52
 * @description
 */
@Slf4j
@RestController
public class ThreadController {

    @Autowired
    private HelloService helloService;

    // 创建线程池
    // private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    // 注入 sleuth 可跟踪的线程池服务
    @Autowired
    private ExecutorService executorService;

    @GetMapping("/testByThread")
    public String testByThread() throws ExecutionException, InterruptedException {
        Future<String> future = executorService.submit(() -> {
            log.info("in thread");
            return helloService.hello();
        });
        // 获取结果
        String result = future.get();
        log.info("thread result: {}", result);
        return result;
    }

}
