package com.moon.user.service;

import com.alibaba.fastjson.JSON;
import com.moon.domain.Order;
import com.moon.domain.User;
import com.moon.user.dao.UserDao;
import com.moon.user.utils.SmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云短信服务
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-07 18:17
 * @description
 */
@Slf4j
@Service("smsServiceSample")
// @RocketMQMessageListener 注解用于配置消费者相关信息
@RocketMQMessageListener(
        consumerGroup = "consume-user",         // 消费者分组
        topic = "alibaba-sample-sms-topic",     // 要消费的主题
        consumeMode = ConsumeMode.CONCURRENTLY, // 消费模式:无序和有序，默认是无序
        messageModel = MessageModel.CLUSTERING  // 消息模式:广播和集群，默认是集群
)
public class SmsService implements RocketMQListener<Order> {

    @Autowired
    private UserDao userDao;

    @Override
    public void onMessage(Order message) {
        log.info("接收到了一个订单信息消息：{}，准备发送短信通知", JSON.toJSONString(message));

        // 根据用户 id 查询用户信息
        User user = userDao.findById(message.getUserId()).get();
        log.info("根据用户 id 获取用户信息：{}", JSON.toJSONString(user));

        Map<String, String> params = new HashMap<>();
        // 设置验证码，简单写死
        params.put("code", "123456");

        try {
            // 发送短信的模拟 {"code":"123456"}
            SmsUtil.sendSms("13800000000", "短信信息", "SMS_170836451", JSON.toJSONString(params));
            log.info("短信发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
