package com.moon.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.moon.order.service.SentinelDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Sentinel 测试的接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-02 18:23
 * @description
 */
@Service
@Slf4j
public class SentinelDemoServiceImpl implements SentinelDemoService {

    // Sentinel 支持通过 @SentinelResource 注解定义资源
    @SentinelResource("message")
    @Override
    public void message() {
        log.info("SentinelDemoServiceImpl.message 方法执行....");
    }

}
