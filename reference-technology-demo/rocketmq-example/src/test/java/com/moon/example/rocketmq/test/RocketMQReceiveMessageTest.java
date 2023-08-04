package com.moon.example.rocketmq.test;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.Test;

import java.util.List;

/**
 * RocketMQ 消息消费测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-06 16:48
 * @description
 */
public class RocketMQReceiveMessageTest {

    @Test
    public void basicTest() throws Exception {
        // 1. 创建消费者，并且为其指定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-consumer-group");

        // 2. 为消费者设置 NameServer 的地址
        consumer.setNamesrvAddr("127.0.0.1:9876");

        /*
         * 3. 指定消费者订阅的主题和标签
         *  String topic            消息主题名称
         *  String subExpression    消息标签名称（`*`代表所有标签）
         */
        consumer.subscribe("myTopic", "*");

        // 4. 设置一个回调函数,并在函数中编写接收到消息之后的处理方法
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            // 处理获取到的消息
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                // 消费逻辑
                System.out.println("Message ===> " + list);

                // 返回消费成功状态
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        // 5. 启动消费者
        consumer.start();
        System.out.println("启动消费者成功了");
        System.in.read();
    }

}
