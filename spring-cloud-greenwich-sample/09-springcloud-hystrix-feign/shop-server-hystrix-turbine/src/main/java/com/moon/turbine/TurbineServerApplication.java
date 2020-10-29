package com.moon.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * hystrix turbine 聚合监控平台启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-19 15:18
 * @description
 */
@SpringBootApplication
@EnableTurbine // 开启 Turbine
@EnableHystrixDashboard // 开启Hystrix Dashboard监控平台
public class TurbineServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurbineServerApplication.class, args);
    }

}
