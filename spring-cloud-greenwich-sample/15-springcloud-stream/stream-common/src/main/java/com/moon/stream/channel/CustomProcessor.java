package com.moon.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义的消息通道
 * 1. 定义消息通道的名称（输入/输出）
 * 2. 使用@Input与@Output注解修饰消息消费与生产的方法（方法名随意）
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-26 16:07
 * @description
 */
public interface CustomProcessor {

    /* 定义输入通道名称 */
    String INPUT = "customInput";

    /* 定义输出通道名称 */
    String OUTPUT = "customOutput";

    /* 定义消息消费者的配置 */
    @Input(CustomProcessor.INPUT)
    SubscribableChannel input();

    /* 定义消息生产者的配置 */
    @Output(CustomProcessor.OUTPUT)
    MessageChannel output();

}
