package com.moon.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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

    private static int count = 0;

    // Sentinel 支持通过 @SentinelResource 注解定义资源
    @SentinelResource("message")
    @Override
    public void message() {
        log.info("SentinelDemoServiceImpl.message 方法执行....");
    }

    /*
     * @SentinelResource 注解是用于指定需要 Sentinel 保护的方法上
     *  blockHandler属性：声明熔断时调用的降级方法
     *  fallback属性：声明抛出异常时执行的降级方法
     *  value属性：设置自定义的资源名称，如不设置，默认值是“当前全类名.方法名”
     */
    @SentinelResource(value = "sentinelResourceBlockHandler", blockHandler = "blockHandler")
    @Override
    public String sentinelResourceBlockHandler(String text) {
        return "BlockException 异常处理测试方法。函数默认需要和原方法在同一个类中";
    }

    @SentinelResource(value = "sentinelResourceFallback", fallback = "fallback")
    @Override
    public String sentinelResourceFallback(String text) {
        count++;
        if (count % 3 == 0) {
            throw new RuntimeException("发生异常了。");
        }

        return "抛出异常处理测试方法。函数默认需要和原方法在同一个类中";
    }

    /*
     * 定义@SentinelResource注解相应的熔断降级方法，函数的要求：
     *  1.必须是public修饰
     *  2.返回类型与原方法一致
     *  3.参数类型需要和原方法相匹配，并在最后加BlockException类型的参数
     *  4.默认需和原方法在同一个类中。若希望使用其他类的函数，可配置blockHandlerClass属性，并指定blockHandlerClass里面的方法，注意函数必需为 `static` 修饰的
     */
    public String blockHandler(String text, BlockException e) {
        log.info("当前方法入参text: {}", text);
        e.printStackTrace();
        return "触发本类内熔断的降级方法";
    }

    /*
     * 定义@SentinelResource注解相应的抛出异常的降级方法，函数的要求：
     *  1.返回类型与原方法一致
     *  2.参数类型需要和原方法相匹配，Sentinel 1.6开始，也可在方法最后加Throwable类型的参数
     *  3.默认需和原方法在同一个类中。若希望使用其他类的函数，可配置fallbackClass，并指定fallbackClass里面的方法，注意函数必需为 `static` 修饰的
     */
    public String fallback(String text, Throwable throwable) {
        log.info("当前方法入参text: {}", text);
        throwable.printStackTrace();
        return "触发本类内抛出异常执行的降级方法";
    }

    @SentinelResource(value = "sentinelResourceBlockHandlerOut",
            blockHandlerClass = BlockHandlerOutDemo.class,
            blockHandler = "blockHandler")
    @Override
    public String sentinelResourceBlockHandlerOut(String text) {
        return "外部类方式处理 BlockException 异常的测试方法";
    }

    @SentinelResource(value = "sentinelResourceFallbackOut",
            fallbackClass = FallbackOutDemo.class,
            fallback = "fallback")
    @Override
    public String sentinelResourceFallbackOut(String text) {
        count++;
        if (count % 3 == 0) {
            throw new RuntimeException("发生异常了。");
        }
        return "外部类方式处理抛出异常处理的测试方法";
    }

}
