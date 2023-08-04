package com.moon.order.filter;

import brave.propagation.ExtraFieldPropagation;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义 Sleuth Tracefilter
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-12 21:42
 * @description
 */
@Component
public class MyTraceFilter extends GenericFilterBean {

    // 过滤方法
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        // 1. 从 request 中取出 header
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String baggageId = request.getHeader("BaggageId");
        // 2. 封装包裹
        ExtraFieldPropagation.set("BaggageId", baggageId);
        // 3. 放行
        chain.doFilter(servletRequest, servletResponse);
    }
}
