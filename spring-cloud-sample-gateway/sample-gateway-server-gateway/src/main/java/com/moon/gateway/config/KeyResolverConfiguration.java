package com.moon.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * KeyResolver配置类，创建 KeyResolver 接口实例，定义不同请求类型的限流键与规则
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-7 09:17
 * @description
 */
@Configuration
public class KeyResolverConfiguration {

    /**
     * 基于请求路径的限流
     *
     * @return
     */
    @Bean("keyResolver")
    public KeyResolver pathKeyResolver() {
        // 示例：根据请求路径做限流依据（路径的值会作为redis的key）
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                return Mono.just(exchange.getRequest().getPath().toString());
            }
        };
    }

    /**
     * 基于请求参数的限流
     *
     * @return
     */
    // @Bean("keyResolver")
    public KeyResolver userKeyResolver() {
        // 示例：根据请求参数中的userId做限流依据（userId的值会作为redis的key）
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("userId"));
    }

    /**
     * 基于请求ip地址的限流
     *
     * @return
     */
    // @Bean("keyResolver")
    public KeyResolver ipKeyResolver() {
        // 示例：根据请求ip做限流依据（ip的值会作为redis的key）
        return exchange -> Mono.just(exchange.getRequest().getHeaders().getFirst("X-Forwarded-For"));
    }

}
