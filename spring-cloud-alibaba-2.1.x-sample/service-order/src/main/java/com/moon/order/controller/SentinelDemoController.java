package com.moon.order.controller;

import com.moon.order.service.SentinelDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Sentinel 基础使用示例请求控制类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-02 14:06
 * @description
 */
@RestController
@RequestMapping("demo/sentinel")
public class SentinelDemoController {

    @Autowired
    private SentinelDemoService sentinelDemoService;

    int i = 0;

    @RequestMapping("message1")
    public String getMessage1() {
        return "message1";
    }

    /* 用于测试关联流控模式：当 /message2 被访问超出限制次数时，/message1 会被限流 */
    @RequestMapping("message2")
    public String getMessage2() {
        return "message2";
    }

    /*
     * 用于测试链路流控模式：当 /message3 与 /message4 都调用同样的受保护资源 message，
     * 可以指定其中一个受流量控制，另一个不受流量控制
     */
    @RequestMapping("message3")
    public String getMessage3() {
        sentinelDemoService.message();
        return "message3";
    }

    @RequestMapping("message4")
    public String getMessage4() {
        sentinelDemoService.message();
        return "message4";
    }

    /* 模拟出现异常的请求方法 */
    @RequestMapping("message5")
    public String getMessage5() {
        i++;
        // 模拟异常出现的机率是0.333
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "message5";
    }

}
