package com.moon.order.exception;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * Sentinel 对 RestTemplate 支持的熔断降级处理类
 * 其中 blockHandler 或 fallback 属性对应的方法必须是对应 blockHandlerClass 或 fallbackClass 属性中的静态方法。
 * 1. 熔断与异常的处理方法都必须为static修饰的静态方法
 * 2. 方法的返回值必须为SentinelClientHttpResponse对象
 * 3. 方法的形参为 HttpRequest request, byte[] body, ClientHttpRequestExecution execution, BlockException ex
 * <p>
 * 即该方法的参数跟返回值跟 org.springframework.http.client.ClientHttpRequestInterceptor#interceptor 方法一致，
 * 其中参数多出了一个 BlockException 参数用于获取 Sentinel 捕获的异常。
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-22 10:53
 * @description
 */
public class ExceptionUtil {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionUtil.class);

    // 熔断降级业务逻辑
    public static SentinelClientHttpResponse handleBlock(HttpRequest request, byte[] body,
                                                         ClientHttpRequestExecution execution, BlockException ex) {
        LOGGER.error("熔断降级业务逻辑：${}", ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse("handleBlock");
    }

    // 异常降级业务逻辑
    public static SentinelClientHttpResponse handleFallback(HttpRequest request, byte[] body,
                                                            ClientHttpRequestExecution execution, BlockException ex) {
        LOGGER.error("异常降级业务逻辑：${}", ex.getClass().getCanonicalName());
        return new SentinelClientHttpResponse("handleFallback");
    }

}
