package com.moon.order.controller;

import com.moon.entity.Product;
import com.moon.order.feign.ProductFeignClient;
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

    // 注入FeignClient服务调用接口
    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * 根据商品id创建订单
     *
     * @param id 商品的id
     * @return
     */
    @PostMapping("/{id}")
    public String createOrder(@PathVariable Long id) {
        // 使用Feign组件实现服务远程调用，直接调用FeignClient的接口定义的相应方法即可
        Product product = productFeignClient.findById(id);
        LOGGER.info("当前下单的商品是: ${}", product);
        if (product.getId() == null) {
            return "创建订单失败，系统繁忙";
        }
        return "创建订单成功";
    }

}
