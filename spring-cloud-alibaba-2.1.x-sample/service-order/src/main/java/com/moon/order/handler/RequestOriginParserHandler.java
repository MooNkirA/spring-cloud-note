package com.moon.order.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义 sentinel 来源访问控制（黑白名单）处理类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-03 17:51
 * @description
 */
// @Component
public class RequestOriginParserHandler implements RequestOriginParser {

    /**
     * Parse the origin from given HTTP request.
     *
     * @param request HTTP request
     * @return parsed origin
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        /* 定义区分来源: 本质作用是通过 HttpServletRequest 对象获取请求一些信息 */
        String client = request.getParameter("client");
        if (StringUtils.isEmpty(client)) {
            throw new RuntimeException("client is not empty");
        }
        return client;
    }

}
