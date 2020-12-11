package com.moon.stream.test;

import com.moon.stream.message.CustomMessageSender;
import com.moon.stream.message.MessageSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 消息生产者测试类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-15 13:41
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private CustomMessageSender customMessageSender;

    /* 测试发送消息 */
    @Test
    public void sendMessage() {
        messageSender.send("Hello, Spring Cloud Stream Producer!");
    }

    /* 测试自定义消息通道发送消息 */
    @Test
    public void sendMessageByCustomChannel() {
        customMessageSender.send("Hello, send message from custom channel!");
    }

    /* 测试消息分区支持功能 */
    @Test
    public void testMessagePartitioningSupport() {
        for (int i = 0; i < 5; i++) {
            customMessageSender.send(0);
        }
    }
}
