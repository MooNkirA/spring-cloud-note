package com.moon.order.controller;

import com.moon.domain.Order;
import com.moon.domain.Product;
import com.moon.order.service.OrderService;
import com.moon.order.service.ProductFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:13
 * @description
 */
@RestController
@RequestMapping("order")
@Slf4j
public class OrderFeignController {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private OrderService orderService;

    /**
     * 下单请求方法，通过 Feign 实现负载均衡的服务调用
     *
     * @param pid
     * @return
     */
    @GetMapping("/feign/{pid}")
    public Order feignLoadBalanced(@PathVariable("pid") Long pid) {
        log.info("接收到ID为{}的商品下单请求", pid);

        /* 通过 FeignClient 接口调用本地方法的方式，实现服务的调用。 */
        Product product = productFeignClient.findById(pid);

        Order order = new Order();
        if (product != null) {

            // 增加 feign 调用容错返回的判断
            if (product.getId().compareTo(Long.parseLong("-1")) == 0) {
                order.setProductId(product.getId());
                order.setProductName(product.getProductName());
                return order;
            }

            order.setProductId(pid);
            order.setNumber(1);
            order.setPrice(product.getPrice());
            order.setAmount(product.getPrice());
            order.setProductName(product.getProductName());
            // 暂写死用户
            order.setUserId(Long.parseLong("0"));
            order.setUsername("测试用户");

            // 创建订单
            orderService.createOrder(order);
        }

        return order;
    }

}
