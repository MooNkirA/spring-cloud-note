package com.moon.order.controller;

import com.moon.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
        /*
         * 通过http请求，获取商品数据
         * 拼接请求url，将原来使用ip+端口调用的方式，改成要调用的服务对应的名称即可
         * 服务提供者名称在其项目配置文件的spring.application.name属性中定义
         */
        Product product = restTemplate.getForObject("http://shop-service-product/product/" + id, Product.class);
        LOGGER.info("当前下单的商品是: ${}", product);
        return "创建订单成功";
    }

}
