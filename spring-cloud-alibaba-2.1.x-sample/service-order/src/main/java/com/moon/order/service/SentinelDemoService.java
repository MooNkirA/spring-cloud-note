package com.moon.order.service;

/**
 * Sentinel 测试的接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-02 18:23
 * @description
 */
public interface SentinelDemoService {

    void message();

    String sentinelResourceBlockHandler(String text);

    String sentinelResourceFallback(String text);

    String sentinelResourceBlockHandlerOut(String text);

    String sentinelResourceFallbackOut(String text);

}
