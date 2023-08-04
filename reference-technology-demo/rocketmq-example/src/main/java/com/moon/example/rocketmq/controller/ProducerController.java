package com.moon.example.rocketmq.controller;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot 方式发送消息
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-07 14:55
 * @description
 */
@RestController
public class ProducerController {

    private final static Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("sendMessage/{msg}")
    public String sendMessage(@PathVariable String msg) {
        logger.info("开始发送消息");
        // 发送异步消息
        rocketMQTemplate.asyncSend("spring-boot-test-topic", msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                logger.info("发送消息成功，结果：{}", sendResult);
            }

            @Override
            public void onException(Throwable e) {
                logger.error("发送消息异常：", e);
            }
        });
        return "send message success!" + msg;
    }

}
