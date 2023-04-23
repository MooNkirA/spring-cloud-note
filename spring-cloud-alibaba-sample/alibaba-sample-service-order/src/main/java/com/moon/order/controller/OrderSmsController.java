package com.moon.order.controller;

import com.moon.domain.Order;
import com.moon.domain.Product;
import com.moon.order.service.OrderService;
import com.moon.order.service.ProductFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单通过 RocketMQ 发送消息与短信服务综合案例
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-07 18:37
 * @description
 */
@Slf4j
@RestController
@RequestMapping("order_sms")
public class OrderSmsController {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 下单请求方法，通过 Feign 服务调用并发送短信
     *
     * @param pid
     * @return
     */
    @GetMapping("/createOrder/{pid}")
    public Order createOrderAndSendMessage(@PathVariable("pid") Long pid) {
        log.info("接收到ID为{}的商品下单请求", pid);

        /* 通过 FeignClient 接口调用本地方法的方式，实现服务的调用。 */
        Product product = productFeignClient.findById(pid);

        Order order = new Order();
        if (product != null) {
            order.setProductId(pid);
            order.setNumber(1);
            order.setPrice(product.getPrice());
            order.setAmount(product.getPrice());
            order.setProductName(product.getProductName());
            // 暂写死用户
            order.setUserId(Long.parseLong("1"));
            order.setUsername("夜神月");

            // 创建订单
            orderService.createOrder(order);

            /*
             * void convertAndSend(D destination, Object payload) throws MessagingException;
             *   发送异步消息到 RocketMQ
             *  D destination：主题名称
             *  Object payload：消息体
             */
            rocketMQTemplate.convertAndSend("alibaba-sample-sms-topic", order);
        }

        return order;
    }

}
