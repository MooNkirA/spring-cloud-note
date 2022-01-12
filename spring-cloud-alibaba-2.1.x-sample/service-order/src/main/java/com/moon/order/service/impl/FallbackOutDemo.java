package com.moon.order.service.impl;

import lombok.extern.slf4j.Slf4j;

/**
 * 定义 sentinel fallback 函数的外部类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-04 10:31
 * @description
 */
@Slf4j
public class FallbackOutDemo {

    /*
     * 定义@SentinelResource注解相应的抛出异常的降级方法，函数的要求：
     *  1.返回类型与原方法一致
     *  2.参数类型需要和原方法相匹配，Sentinel 1.6开始，也可在方法最后加Throwable类型的参数
     *  3.默认需和原方法在同一个类中。若希望使用其他类的函数，可配置fallbackClass，并指定fallbackClass里面的方法，注意函数必需为 `static` 修饰的
     */
    public static String fallback(String text, Throwable throwable) {
        log.info("当前方法入参text: {}", text);
        throwable.printStackTrace();
        return "外部类方式处理抛出异常处理";
    }
}
