package com.moon.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义局部过滤器。具体要求如下：
 * <p>1. 类的名称必须是以`GatewayFilterFactory`结尾。即`自定义局部过滤器名称+GatewayFilterFactory`</p>
 * <p>2. 类必须继承 `AbstractGatewayFilterFactory<配置类>` 抽象类</p>
 * <p>
 * <p>
 * 提示：技巧提示：如不知道如何实现这些接口，可以通过参考框架自身内置的实现来进行改造^_^
 * </p>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-05 14:17
 * @description
 */
// AbstractGatewayFilterFactory 的泛型 C 是一个配置类，配置类用于接收中在配置文件（properties/yml）中的配置值
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.Config> {

    // 构造函数，调用父类构造器，并传入配置类
    public LogGatewayFilterFactory() {
        super(LogGatewayFilterFactory.Config.class);
    }

    /**
     * 读取配置文件的中参数值，赋值到配置类中的属性上
     *
     * @return
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog", "cacheLog");
    }

    /**
     * 过滤器的核心处理逻辑
     *
     * @param config
     * @return
     */
    @Override
    public GatewayFilter apply(LogGatewayFilterFactory.Config config) {
        return (exchange, chain) -> {
            // 对配置的参数判断
            if (config.isCacheLog()) {
                System.out.println("cacheLog已经开启了....");
            }
            if (config.isConsoleLog()) {
                System.out.println("consoleLog已经开启了....");
            }
            // 传递过滤器链
            return chain.filter(exchange);
        };
    }

    /**
     * 配置类，用于接收在配置文件中的对应参数值
     */
    public static class Config {
        private boolean consoleLog;
        private boolean cacheLog;

        public Config() {
        }

        public boolean isConsoleLog() {
            return consoleLog;
        }

        public void setConsoleLog(boolean consoleLog) {
            this.consoleLog = consoleLog;
        }

        public boolean isCacheLog() {
            return cacheLog;
        }

        public void setCacheLog(boolean cacheLog) {
            this.cacheLog = cacheLog;
        }
    }
}
