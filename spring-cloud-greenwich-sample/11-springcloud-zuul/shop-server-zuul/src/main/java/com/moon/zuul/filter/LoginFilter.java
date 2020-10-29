package com.moon.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * zuul自定义过滤器示例 - 请求登陆校验过滤
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-24 11:20
 * @description
 */
@Component // 自定义zuul过滤器，需要使用@Component标识该bean给spring容器管理
// 自定义zuul过滤器需要继承抽象父类ZuulFilter
public class LoginFilter extends ZuulFilter {

    /**
     * 设置过滤器类型
     * pre：请求在被路由之前执行
     * routing：在路由请求时调用
     * post：在routing和errror过滤器之后调用
     * error：处理请求时发生错误调用
     *
     * @return 过滤器的类型字符串
     */
    @Override
    public String filterType() {
        // 登录校验，设置为请求路由之前拦截
        return "pre";
    }

    /**
     * 指定过滤器的执行顺序，返回值越小越被优先执行
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 过滤器是否需要执行
     *
     * @return true: 执行此过滤器，false: 不执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的业务逻辑方法
     * <p>
     * 此示例完成身份认证:
     * 1.所有的请求需要携带一个参数: access-token
     * 2.获取request请求头"access-token"
     * 3.判断token是否为空
     * 4.1 token==null : 身份验证失败
     * 4.2 token!=null : 执行后续操作
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 在zuul网关中，通过RequestContext的上下文对象，可以获取HttpServletRequest和HttpServletResponse对象
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        // 获取请求头中access-token字段
        String token = request.getHeader("access-token");

        if (StringUtils.isEmpty(token)) {
            // 如果请求头不包含Authorization，则拒绝访问
            context.setSendZuulResponse(false);
            // 设置响应状态码
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            // 设置JSON格式的响应信息
            context.setResponseBody("{\"code\": \"-1\", \"retMsg\": \"此操作需要登陆系统！\"}");
            // 设置contentType为json类型
            response.setContentType("application/json;charset=UTF-8");
            // 还可以进行其他的操作
        }
        // 校验通过，返回继续向后执行。（也可以考虑把用户信息放入上下文）
        return null;
    }

}
