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

    // 注入注册中心服务对象
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 根据商品id创建订单
     *
     * @param id 商品的id
     * @return
     */
    @PostMapping("/{id}")
    public String createOrder(@PathVariable Long id) {
        // 调用discoveryClient方法，根据服务名称获取所有的元数据
        List<ServiceInstance> instances = discoveryClient.getInstances("shop-service-product");
        // 因为当前商品服务只有一个，所以直接获取唯一的服务实例即可
        ServiceInstance productService = instances.get(0);

        // 拼接请求url
        String uri = String.format("http://%s:%s/product/%d", productService.getHost(), productService.getPort(), id);

        // 通过http请求，获取商品数据
        Product product = restTemplate.getForObject(uri, Product.class);
        LOGGER.info("当前下单的商品是: ${}", product);
        return "创建订单成功";
    }

}
