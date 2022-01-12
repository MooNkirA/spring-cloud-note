package com.moon.order.controller;

import com.moon.domain.Order;
import com.moon.domain.Product;
import com.moon.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

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
public class OrderRibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    /**
     * 下单请求方法，通过 ribbon 进行负载均衡
     *
     * @param pid
     * @return
     */
    @GetMapping("/ribbon/{pid}")
    public Order ribbonLoadBalanced(@PathVariable("pid") Long pid) {
        log.info("接收到ID为{}的商品下单请求", pid);

        /* RestTemplate 接入 Ribbon 后，将原来的url+端口替换成注册中心上的服务名称 */
        Product product = restTemplate
                .getForObject(String.format("http://service-product/product/%s", pid), Product.class);

        Order order = new Order();
        if (product != null) {
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
