package com.moon.example.rocketmq.service;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Spring Boot 方式接收消息。
 * 消息消费者需要 实现 RocketMQListener<T> 接口。泛型 T 是消息的类型
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-07 15:03
 * @description
 */
@Service
// @RocketMQMessageListener 注解用于配置消费者相关信息
@RocketMQMessageListener(
        consumerGroup = "consume-example",      // 消费者分组
        topic = "spring-boot-test-topic",       // 要消费的主题
        consumeMode = ConsumeMode.CONCURRENTLY, // 消费模式:无序和有序，默认是无序
        messageModel = MessageModel.CLUSTERING  // 消息模式:广播和集群，默认是集群
)
public class ConsumeService implements RocketMQListener<String> {

    private final static Logger logger = LoggerFactory.getLogger(ConsumeService.class);

    @Override
    public void onMessage(String message) {
        logger.info("消息消费者接收到的信息：{}", message);
    }

}
