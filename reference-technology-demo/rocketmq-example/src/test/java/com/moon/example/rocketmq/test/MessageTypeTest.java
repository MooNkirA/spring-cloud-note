package com.moon.example.rocketmq.test;

import com.moon.example.rocketmq.RocketMQApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * RocketMQ 消息类型测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-06 23:12
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RocketMQApplication.class)
public class MessageTypeTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    // 同步消息测试
    @Test
    public void testSyncSend() {
        /*
         * public SendResult syncSend(String destination, Object payload, long timeout)
         *  发送同步消息，在等待消息发送结果的返回之前，会一直阻塞
         *  String destination  消息主题和标签。格式：`topicName:tags`
         *  Object payload      消息体
         *  long timeout        超时时间，单位ms
         */
        SendResult result =
                rocketMQTemplate.syncSend("MessageType-test-topic:sync", "这是一条同步消息", 10000);
        System.out.println(result);
    }

    // 异步消息测试
    @Test
    public void testAsyncSend() throws InterruptedException {
        /*
         * public void asyncSend(String destination, Object payload, SendCallback sendCallback)
         *  发送异步消息，发送消息之后，程序会继续往下执行，不会等待结果的返回
         *  String destination          消息主题和标签。格式：`topicName:tags`
         *  Object payload              消息体
         *  SendCallback sendCallback   异步消息发送成功的回调函数
         */
        rocketMQTemplate.asyncSend("MessageType-test-topic:async", "这是一条异步消息", new SendCallback() {
            // 成功响应的回调
            @Override
            public void onSuccess(SendResult result) {
                System.out.println(result);
            }

            // 异常响应的回调
            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        });
        System.out.println("异步消息发送成功");
        // 线程睡眠，目的等待异步消息发送成功后的回调函数
        Thread.sleep(30000);
    }

    // 单向消息测试
    @Test
    public void testOneWay() {
        /*
         * public void sendOneWay(String destination, Object payload)
         *  发送单向消息，不等待服务器回应且没有回调函数触发
         *  String destination  消息主题和标签。格式：`topicName:tags`
         *  Object payload      消息体
         */
        rocketMQTemplate.sendOneWay("MessageType-test-topic:oneway", "这是一条单向消息");
    }

    // 顺序消息测试
    @Test
    public void testSendOrderly() throws InterruptedException {
        /*
         * public SendResult syncSendOrderly(String destination, Message<?> message, String hashKey, long timeout)
         *  发送同步顺序消息，在等待消息发送结果的返回之前，会一直阻塞
         *  String destination  消息主题和标签。格式：`topicName:tags`
         *  Object payload      消息体
         *  String hashKey      用于选择队列的 hashkey。
         *  long timeout        超时时间，单位ms
         */
        SendResult result =
                rocketMQTemplate.syncSendOrderly("MessageType-test-topic:sync", "这是一条同步消息", "hk", 10000);
        System.out.println(result);
        /*
         * public void asyncSendOrderly(String destination, Object payload, String hashKey, SendCallback sendCallback)
         *  发送异步顺序消息，发送消息之后，程序会继续往下执行，不会等待结果的返回
         *  String destination          消息主题和标签。格式：`topicName:tags`
         *  Object payload              消息体
         *  String hashKey              用于选择队列的 hashkey。
         *  SendCallback sendCallback   异步消息发送成功的回调函数
         */
        rocketMQTemplate.asyncSendOrderly("MessageType-test-topic:async", "这是一条异步消息", "hk", new SendCallback() {
            // 成功响应的回调
            @Override
            public void onSuccess(SendResult result) {
                System.out.println(result);
            }

            // 异常响应的回调
            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        });

        /*
         * public void sendOneWayOrderly(String destination, Object payload, String hashKey)
         *  发送单向顺序消息，不等待服务器回应且没有回调函数触发
         *  String destination  消息主题和标签。格式：`topicName:tags`
         *  Object payload      消息体
         *  String hashKey      用于选择队列的 hashkey。
         */
        rocketMQTemplate.sendOneWayOrderly("MessageType-test-topic:oneway", "这是一条单向消息", "hk");
        // 线程睡眠，目的等待异步消息发送成功后的回调函数
        Thread.sleep(30000);
    }



}
