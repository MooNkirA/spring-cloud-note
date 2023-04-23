package com.moon.gateway.predicates;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义路由断言工厂类。具体要求如下：
 * <p>1. 类的名称必须是以`RoutePredicateFactory`结尾。即`自定义断言配置名称+RoutePredicateFactory`</p>
 * <p>2. 类必须继承 `AbstractRoutePredicateFactory<配置类>` 抽象类</p>
 * <p>
 * <p>
 * 提示：技巧提示：如不知道如何实现这些接口，可以通过参考框架自身内置的实现来进行改造^_^
 * </p>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-05 11:00
 * @description
 */
// AbstractRoutePredicateFactory 的泛型 C 是一个配置类，配置类用于接收中在配置文件（properties/yml）中的配置值
// @Component
public class CustomRoutePredicateFactory extends AbstractRoutePredicateFactory<CustomRoutePredicateFactory.Config> {

    // 构造函数，调用父类构造器，并传入配置类
    public CustomRoutePredicateFactory() {
        super(CustomRoutePredicateFactory.Config.class);
    }

    /*
     * 读取配置文件的中参数值，赋值到配置类中的属性上
     */
    @Override
    public List<String> shortcutFieldOrder() {
        // 注意，定义返回集合中元素的位置的顺序，必须跟配置文件中的参数值的顺序相对应
        return Arrays.asList("min", "max");
    }

    /**
     * 断言核心的处理逻辑
     *
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(CustomRoutePredicateFactory.Config config) {
        // Predicate<T> 是 jdk8 的函数式接口，可以直接使用 lambda 表达式
        return serverWebExchange -> {
            // 获取请求传的参数 range 的值
            String range = serverWebExchange.getRequest().getQueryParams().getFirst("range");

            // 对范围参数进行判断
            if (StringUtils.hasText(range)) {
                // 非空，则进行范围的判断
                int num = Integer.parseInt(range);
                return num >= config.getMin() && num <= config.getMax();
            }

            return false;
        };
    }

    /**
     * 配置类，用于接收在配置文件中的对应参数值
     */
    public static class Config {
        private int min;
        private int max;

        public Config() {
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }
    }

}
