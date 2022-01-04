package com.moon.order.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.moon.common.api.CommonResult;
import com.moon.common.api.ResultCode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * sentinel 自定义限制异常提示（1.8版本之前的版本）
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-03 18:30
 * @description
 */
// @Component
public class ExceptionPageHandlerOlderVersion implements UrlBlockHandler {

    /**
     * Handle the request when blocked.
     *
     * @param request  Servlet request
     * @param response Servlet response
     * @param ex       the block exception.
     * @throws IOException some error occurs
     */
    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        CommonResult responseData = null;
        // BlockException 是 Sentinel 异常接口,包含 Sentinel 的五种异常
        if (ex instanceof FlowException) {
            // FlowException  限流异常
            responseData = CommonResult.failed(ResultCode.SENTINEL_FLOW);
        } else if (ex instanceof DegradeException) {
            // DegradeException  降级异常
            responseData = CommonResult.failed(ResultCode.SENTINEL_DEGRADE);
        } else if (ex instanceof ParamFlowException) {
            // ParamFlowException  参数限流异常
            responseData = CommonResult.failed(ResultCode.SENTINEL_PARAM_FLOW);
        } else if (ex instanceof AuthorityException) {
            // AuthorityException  授权异常
            responseData = CommonResult.failed(ResultCode.SENTINEL_AUTHORITY);
        } else if (ex instanceof SystemBlockException) {
            // SystemBlockException  系统负载异常
            responseData = CommonResult.failed(ResultCode.SENTINEL_SYSTEM_BLOCK);
        }
        response.getWriter().write(JSON.toJSONString(responseData));
    }

}
