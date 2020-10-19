package com.moon.order.controller;

import com.moon.entity.Product;
import com.moon.order.command.OrderCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 订单控制类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-8 09:46
 * @description
 */
@RestController
@RequestMapping("order")
public class OrderController {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    // 注入HTTP请求工具类RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 根据商品id创建订单
     *
     * @param id 商品的id
     * @return
     */
    @PostMapping("/{id}")
    public String createOrder(@PathVariable Long id) {
        // 使用OrderCommand调用远程服务
        OrderCommand orderCommand = new OrderCommand(restTemplate, id);
        Product product = orderCommand.execute();
        LOGGER.info("当前下单的商品是: ${}", product);
        return "创建订单成功";
    }

    /**
     * 根据ids查询订单方法，注：什么逻辑都没有，直接返回，用于测试高并发请求其他方法时，此普通请求方法的返回时间
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public String findOrder(@PathVariable Long id) {
        System.out.println(Thread.currentThread().getName());
        return "根据id查询订单";
    }

}
