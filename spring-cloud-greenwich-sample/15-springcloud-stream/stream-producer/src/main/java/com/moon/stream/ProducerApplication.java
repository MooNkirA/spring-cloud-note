package com.moon.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Cloud Stream 消息生产者（producer）启动类
 * <p>
 * Spring Cloud Stream 消息生产者的实现步骤：
 * 1. 引入Spring Cloud Stream的依赖
 * 2. 修改application.yml配置消息中间件与stream的相关配置
 * 3. 定义一个通道接口，通过接口中内置的messagechannel
 * 4. 标识@EnableBinding注解绑定对应通道
 * 5. 通过绑定的接口（或内置的Source接口）获取MessageChannel示例，再发送消息
 * </p>
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-15 11:54
 * @description
 */
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

}
