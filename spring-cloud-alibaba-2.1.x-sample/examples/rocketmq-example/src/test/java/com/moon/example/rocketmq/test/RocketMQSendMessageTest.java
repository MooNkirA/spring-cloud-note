package com.moon.example.rocketmq.test;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.junit.Test;

/**
 * RocketMQ 消息发送测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-06 16:37
 * @description
 */
public class RocketMQSendMessageTest {

    // 发送消息测试
    @Test
    public void basicTest() throws Exception {
        // 1. 创建消息生产者，并且设置生产组名
        DefaultMQProducer producer = new DefaultMQProducer("my-producer-group");

        // 2. 为生产者设置 NameServer 的地址
        producer.setNamesrvAddr("127.0.0.1:9876");

        // 3. 启动生产者
        producer.start();

        /*
         * 4. 构建消息对象
         *  String topic 消息的主题名
         *  String tags  消息的标签名
         *  byte[] body  消息的内容，字节数组
         */
        Message message = new Message("myTopic", "myTag", ("Test RocketMQ Message").getBytes());

        /*
         * 5. 发送消息
         *  Message msg     消息对象
         *  long timeout    超时时间
         */
        SendResult result = producer.send(message, 10000);
        System.out.println(result);

        // 6. 关闭生产者
        producer.shutdown();
    }

}
