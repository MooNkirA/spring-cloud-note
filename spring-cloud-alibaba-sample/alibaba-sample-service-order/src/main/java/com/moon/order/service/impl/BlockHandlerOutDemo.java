package com.moon.order.service.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * 定义 sentinel blockHandler 函数的外部类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-04 10:30
 * @description
 */
@Slf4j
public class BlockHandlerOutDemo {

    /*
     * 定义@SentinelResource注解相应的熔断降级方法，函数的要求：
     *  1.必须是public修饰
     *  2.返回类型与原方法一致
     *  3.参数类型需要和原方法相匹配，并在最后加BlockException类型的参数
     *  4.默认需和原方法在同一个类中。若希望使用其他类的函数，可配置blockHandlerClass属性，并指定blockHandlerClass里面的方法，注意函数必需为 `static` 修饰的
     */
    public static String blockHandler(String text, BlockException e) {
        log.info("当前方法入参text: {}", text);
        e.printStackTrace();
        return "外部类方式处理 BlockException 异常";
    }
}
