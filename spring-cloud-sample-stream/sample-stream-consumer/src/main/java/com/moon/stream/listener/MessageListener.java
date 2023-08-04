package com.moon.stream.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Component;

/**
 * 消息监听类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-11-21 23:44
 * @description
 */
@Slf4j
@Component // 注册到spring容器中
@EnableBinding(Sink.class)  // 绑定消息通道，此示例绑定的是Spring Cloud Stream内置的Sink接口
public class MessageListener {

    /**
     * 监听binding中的消息，通过@StreamListener注解指定绑定的名称，
     * 这里使用Spring Cloud Stream内置的Sink接口，名称为“input”
     * (ps. 方法名称随意)
     *
     * @param message
     */
    @StreamListener(Sink.INPUT)
    // 使用 @StreamListener 注解中的 condition 属性，可以去监听的消息进行过滤
    /*@StreamListener(
            value = Sink.INPUT,
            condition = "headers['test-header']=='my test'"
    )*/
    public void input(String message) {
        System.out.println("获取的消息：" + message);
    }

    /**
     * 局部消费异常处理
     * 使用 @ServiceActivator 注解标识异常处理方法，inputChannel 属性指定要进行处理的主题
     * inputChannel 格式：主题.消费者组.errors
     */
    /*@ServiceActivator(
            inputChannel = "topic-test-stream.stream-consumer-group.errors"
    )
    public void handleError(ErrorMessage errorMessage) {
        log.error("局部异常. errorMsg: {}", errorMessage);
    }*/

    /**
     * 全局消费异常处理
     * 使用 @StreamListener 注解标识异常处理方法，使用 errorChannel 处理接口即可
     */
    /*@StreamListener("errorChannel")
    public void handleGlobalError(ErrorMessage errorMessage) {
        log.error("全局异常. errorMsg: {}", errorMessage);
    }*/
}
