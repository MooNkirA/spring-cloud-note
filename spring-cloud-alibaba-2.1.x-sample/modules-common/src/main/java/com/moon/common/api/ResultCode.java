package com.moon.common.api;

/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    VALIDATE_FAILED(402, "参数检验失败"),
    FORBIDDEN(403, "没有相关权限"),
    // Sentinel FlowException  限流异常
    SENTINEL_FLOW(5001, "接口限流"),
    // Sentinel DegradeException  降级异常
    SENTINEL_DEGRADE(5002, "接口降级"),
    // Sentinel ParamFlowException  参数限流异常
    SENTINEL_PARAM_FLOW(5003, "接口热点限流"),
    // Sentinel AuthorityException  授权异常
    SENTINEL_AUTHORITY(5004, "接口授权限制"),
    // Sentinel SystemBlockException  系统负载异常
    SENTINEL_SYSTEM_BLOCK(5005, "系统负载限制");

    private final long code;
    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
