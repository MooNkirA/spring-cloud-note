package com.moon.gateway.dynamic;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;

/**
 * Nacos 连接操作类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-02 17:23
 * @description
 */
@Component
@Slf4j
public class RouteNacos {

    /* 定义连接 Nacos 相关常量 */
    private final String NACOS_SERVER_ADDR = "localhost:8848";
    private final String NACOS_DATA_ID = "gateway-route";
    private final String NACOS_DATA_GROUP = "GATEWAY_GROUP";

    @Autowired
    private MyRouteWriter myRouteWriter;

    /**
     * 服务启动后的处理逻辑
     * 1. 对接 nacos
     * 2. 加载 nacos 已有路由配置
     * 3. 监听 nacos 路由配置的变化
     */
    @PostConstruct
    public void dynamicRoute() {
        try {
            // 1. 连接 nacos，构造 nacos config service
            ConfigService configService = NacosFactory.createConfigService(NACOS_SERVER_ADDR);

            // 2. 加载 nacos 已有路由信息，初始化路由配置
            String config = configService.getConfig(NACOS_DATA_ID, NACOS_DATA_GROUP, 5000);
            log.debug("---route config: {}", config);

            RouteDefinition routeDefinition = JSON.parseObject(config, RouteDefinition.class);

            myRouteWriter.add(routeDefinition);

            // 3. 设置 nacos 监听，配置变动后更新本地配置
            configService.addListener(NACOS_DATA_ID, NACOS_DATA_GROUP, new NacosRouteListener());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class NacosRouteListener implements Listener {

        @Override
        public Executor getExecutor() {
            return null;
        }

        @Override
        public void receiveConfigInfo(String configInfo) {
            log.debug("---route change: {}", configInfo);

            RouteDefinition routeDefinition = JSON.parseObject(configInfo, RouteDefinition.class);

            myRouteWriter.update(routeDefinition);
        }
    }
}
