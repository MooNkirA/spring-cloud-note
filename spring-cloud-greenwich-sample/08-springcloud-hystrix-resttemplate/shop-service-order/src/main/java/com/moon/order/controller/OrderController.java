package com.moon.order.controller;

import com.moon.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
/*
 * @DefaultProperties注解用于指定此接口中公共的熔断设置，
 *  defaultFallback属性：用于公共的降级方法名称
 *  如果过在@DefaultProperties指定了公共的降级方法，在相应的熔断保护@HystrixCommand中不需要单独指定降级方法
 */
@DefaultProperties(defaultFallback = "defaultFallBack")
public class OrderController {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    // 注入HTTP请求工具类RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    /*
     * 标识 @HystrixCommand 注解代表此方法配置熔断保护
     *      fallbackMethod属性：指定熔断之后的降级方法
     */
    // @HystrixCommand(fallbackMethod = "orderFallBack")
    @HystrixCommand // 使用统一降级方法，则不需要再指定fallbackMethod属性
    @PostMapping("/{id}")
    public String createOrder(@PathVariable Long id) {
        // 调用服务
        Product product = restTemplate.getForObject("http://shop-service-product/product/" + id, Product.class);
        LOGGER.info("当前下单的商品是: ${}", product);
        return "创建订单成功";
    }

    /*
     * 公共的降级方法
     *  注意: 1.此方法不能有形参
     *       2.如果使用统一的降级方法，则最好统一所有需要熔断保护的方法的返回类型
     */
    public String defaultFallBack() {
        LOGGER.info("触发熔断公共降级方法");
        return "触发熔断公共降级方法";
    }

    /*
     * 降级方法
     *  和需要收到保护的方法的返回值一致
     *  方法参数一致
     */
    public String orderFallBack(Long id) {
        LOGGER.info("当前下单商品的id是: " + id + "，触发createOrder熔断的降级方法");
        return "当前下单商品的id是: " + id + "，触发createOrder熔断的降级方法";
    }

}
