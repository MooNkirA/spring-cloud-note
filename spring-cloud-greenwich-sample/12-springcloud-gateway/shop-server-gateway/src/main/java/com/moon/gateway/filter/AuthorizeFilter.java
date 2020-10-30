package com.moon.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Spring Cloud Gateway 自定义一个全局过滤器，需要实现 GlobalFilter, Ordered接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-30 11:13
 * @description
 */
@Component // 要自定义全局过滤器生效，需要将全局过滤器注册到spring容器中
public class AuthorizeFilter implements GlobalFilter, Ordered {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizeFilter.class);

    /**
     * 此方法是过滤器执行的主要逻辑
     *
     * @param exchange ServerWebExchange是当前请求和响应的上下文对象，存放着重要的请求-响应属性、请求实例和响应实例等等。(相当于zuul中的RequestContext)
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LOGGER.info("自定义全局过滤器AuthorizeFilter开始执行了....");
        // 通过ServerWebExchange对象可以获取请求与响应实例
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 获取请求头中access-token字段
        String token = request.getHeaders().getFirst("access-token");

        // 简单的模拟校验
        if (StringUtils.isEmpty(token)) {
            // 如果请求头不包含Authorization，则认证失败。记录一下日志
            LOGGER.error("请求{}, 登陆认证失败", request.getURI());
            // 设置响应状态码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 设置请求结束
            return response.setComplete();
        }

        // 如果认证通过，需要调用 chain.filter() 方法才继续向下游执行
        return chain.filter(exchange);
    }

    /**
     * 指定过滤器的执行顺序。返回值越小，执行优先级越高
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
