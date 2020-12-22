package com.moon.stream.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 消息发送工具类，用于负责向消息中间件发送消息
 * 注：@EnableBinding注解用于绑定消息发送通道。可以标识在Spring容器管理的配置bean或者入口类上，但一般建议标识在消息发送相关的类上
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-15 13:30
 * @description
 */
@Component // 注册到spring容器中
@EnableBinding(Source.class) // 绑定消息通道，此示例绑定的是Spring Cloud Stream内置的Source接口
public class MessageSender {

    /* 注入消息通道对象 */
    @Autowired
    /*
     * 此处需要指定注入bean的名称，否则会报错：
     *  org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'org.springframework.messaging.MessageChannel' available:
     *  expected single matching bean but found 3: output,nullChannel,errorChannel
     */
    @Qualifier("output")
    private MessageChannel messageChannel;

    /**
     * 发送消息
     *
     * @param obj 发送的内容
     */
    public void send(Object obj) {
        // 通过消息通过对象，发送MQ消息
        messageChannel.send(MessageBuilder.withPayload(obj).build());
    }

}
