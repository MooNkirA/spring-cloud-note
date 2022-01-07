package com.moon.example.rocketmq.controller;

import com.moon.example.rocketmq.model.Product;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 事务消息测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-07 10:30
 * @description
 */
@RestController
public class TxMessageController {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @GetMapping("tx_message_example")
    public String txMessageExample() {
        System.out.println("======= 程序开始 =======");

        Product product = new Product();
        product.setId("001");
        product.setProductName("秋天的兰花");
        product.setPrice(BigDecimal.TEN);
        product.setProductDesc("オータム オーキッド");

        /*
         * public TransactionSendResult sendMessageInTransaction(final String destination, final Message<?> message, final Object arg)
         *  发送半事务消息。
         *      final String destination    消息主题和标签。格式：`topicName:tags`
         *      final Message<?> message    消息内容
         *      final Object arg            在执行本地事务方法中传入的参数
         */
        rocketMQTemplate.sendMessageInTransaction(
                "tx_topic",
                MessageBuilder.withPayload(product).setHeader("tx_id", "tx-id-001").build(),
                product
        );

        return product.getProductName();
    }

}
