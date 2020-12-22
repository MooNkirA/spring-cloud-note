package com.moon.stream.listener;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/**
 * 消息监听类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-21 23:44
 * @description
 */
@Component // 注册到spring容器中
@EnableBinding(Sink.class)  // 绑定消息通道，此示例绑定的是Spring Cloud Stream内置的Sink接口
public class MessageListener {

    /**
     * 监听binding中的消息，通过@StreamListener注解指定绑定的名称，
     * 这里使用Spring Cloud Stream内置的Sink接口，名称为“input”
     * (ps. 方法名称随意)
     *
     * @param message
     */
    @StreamListener(Sink.INPUT)
    public void input(String message) {
        System.out.println("获取的消息：" + message);
    }

}
