package com.moon.stream.message;

import com.moon.stream.channel.CustomProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 自定义消息通道绑定与消息发送工具类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-26 17:01
 * @description
 */
@Component
@EnableBinding(CustomProcessor.class) // 绑定自定义消息通道接口
public class CustomMessageSender {

    /* 注入自定义消息通道对象 */
    @Autowired
    // @Qualifier(CustomProcessor.OUTPUT) // 指定spring容器中MessageChannel的名称，或者注入的属性名称与容器中beanName一致也可以
    private MessageChannel customOutput;

    /**
     * 发送消息
     *
     * @param obj 发送的内容
     */
    public void send(Object obj) {
        // 通过消息通过对象，发送MQ消息
        customOutput.send(MessageBuilder.withPayload(obj).build());
    }

}
