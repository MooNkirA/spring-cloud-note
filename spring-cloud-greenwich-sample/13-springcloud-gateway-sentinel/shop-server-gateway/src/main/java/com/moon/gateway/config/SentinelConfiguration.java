package com.moon.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Sentinel限流的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-11 16:23
 * @description
 */
@Configuration
public class SentinelConfiguration {

    private final List<ViewResolver> viewResolvers;

    private final ServerCodecConfigurer serverCodecConfigurer;

    /**
     * 构造方法，用于初始化 List<ViewResolver> 与 ServerCodecConfigurer
     *
     * @param viewResolversProvider
     * @param serverCodecConfigurer
     */
    public SentinelConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                 ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 配置限流的异常处理器: SentinelGatewayBlockExceptionHandler
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    /**
     * 配置限流过滤器：GlobalFilter
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * 配置初始化的限流参数，用于指定资源的限流规则，需要的配置项如下：
     * 1. 资源名称 (路由id)
     * 2. 配置统计时间
     * 3. 配置限流阈值
     */
    @PostConstruct
    public void initGatewayRules() {
        // 创建限流规则 GatewayFlowRule 实例的set集合（因为可以指定多个规则）
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(new GatewayFlowRule("shop-service-product") // 指定限流的资源名称
                .setCount(1) // 设置限流的阈值
                .setIntervalSec(1)  // 设置统计时间，单位是秒，默认是 1 秒
        );
        // 添加限流规则到 GatewayRuleManager 管理器
        GatewayRuleManager.loadRules(rules);
    }

}
