package com.moon.stream.listener;

import com.moon.stream.channel.CustomProcessor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * 自定义消息通道的消息监听类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-26 17:15
 * @description
 */
@Component
@EnableBinding(CustomProcessor.class) // 绑定自定义消息通道
public class CustomMessageListener {

    /**
     * 监听自定义消息通道（CustomProcessor.INPUT）的消息(ps. 方法名称随意)
     *
     * @param message
     */
    @StreamListener(CustomProcessor.INPUT)
    public void input(String message) {
        System.out.println("监听自定义消费通道获取的消息：" + message);
    }

}
