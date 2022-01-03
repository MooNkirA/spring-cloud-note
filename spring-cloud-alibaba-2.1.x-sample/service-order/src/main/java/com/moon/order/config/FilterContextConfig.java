package com.moon.order.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1.7.0 版本开始（对应 Spring Cloud Alibaba 的2.1.1.RELEASE)，官方在CommonFilter 引入了 WEB_CONTEXT_UNIFY 参数，
 * 用于控制是否收敛context。将其配置为 false 即可根据不同的 URL 进行链路限流。
 * <p>
 * 注意：如果使用 Spring Cloud Alibaba 2.1.1 及更高的版本都需要手动配置此类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-02 18:42
 * @description
 */
@Configuration
public class FilterContextConfig {

    @Bean
    public FilterRegistrationBean sentinelFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CommonFilter());
        registration.addUrlPatterns("/*");
        // 入口资源关闭聚合
        registration.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
        registration.setName("sentinelFilter");
        registration.setOrder(1);
        return registration;
    }

}
