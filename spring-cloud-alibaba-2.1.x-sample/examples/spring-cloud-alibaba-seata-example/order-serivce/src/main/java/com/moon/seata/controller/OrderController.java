package com.moon.seata.controller;

import com.moon.seata.domain.Order;
import com.moon.seata.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单测试 Seata 分布式事务解决方案
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-09 13:14
 * @description
 */
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 下单请求方法，通过 Feign 调用商品服务扣减库存。用于测试 seata 分布式事务
     *
     * @param pid
     * @return
     */
    @GetMapping("/create/{pid}")
    public Order createOrder(@PathVariable("pid") Long pid) {
        return orderService.createOrder(pid);
    }
}
