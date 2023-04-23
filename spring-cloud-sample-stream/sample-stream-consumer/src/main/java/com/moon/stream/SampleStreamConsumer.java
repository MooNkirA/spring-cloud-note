package com.moon.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Cloud Stream 消息消费者（Consumer）启动类
 * <p>
 * Spring Cloud Stream 消息消费者的实现步骤：
 * 1. 引入Spring Cloud Stream的依赖
 * 2. 修改application.yml配置消息中间件与stream的相关配置
 * 3. 定义一个通道接口，通过接口中内置的通道。此示例使用Spring Cloud Stream内置的Sink接口
 * 4. 标识@EnableBinding注解绑定对应通道
 * 5. 配置一个监听方法：当程序从中间件获取数据之后，执行的业务逻辑方法，需要在监听方法上标识 @StreamListener 注解
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-22 08:52
 * @description
 */
@SpringBootApplication
public class SampleStreamConsumer {

    public static void main(String[] args) {
        SpringApplication.run(SampleStreamConsumer.class, args);
    }

}
