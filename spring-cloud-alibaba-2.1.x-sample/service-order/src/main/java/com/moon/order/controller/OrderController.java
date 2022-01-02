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
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    /**
     * 下单请求方法，通过 nacos 服务注册中心获取商品服务地址和端口，
     * 自定义实现负载均衡（随机）
     *
     * @param pid
     * @return
     */
    @GetMapping("/customLoadBalanced/{pid}")
    public Order customLoadBalanced(@PathVariable("pid") Long pid) {
        log.info("接收到ID为{}的商品下单请求", pid);

        /*
         * 通过 DiscoveryClient 的 getInstances 方法可以获取到指定的服务实例对象（返回值是集合，因为服务会有集群的情况）。
         * 通过服务实例对象，可获取服务的地址和端口
         */
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        // 定义随机数
        int index = new Random().nextInt(instances.size());
        // 随机获取列表的服务实例，从而简单实现负载均衡（随机）
        ServiceInstance serviceInstance = instances.get(index);
        String host = serviceInstance.getHost(); // 地址
        int port = serviceInstance.getPort(); // 端口
        log.info("调用端口号为{}的商品微服务", port);
        Product product = restTemplate
                .getForObject(String.format("http://%s:%d/product/%s", host, port, pid), Product.class);

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

    /**
     * 下单请求方法，通过 nacos 服务注册中心获取商品服务地址和端口，再调用商品查询接口
     *
     * @param pid
     * @return
     */
    @GetMapping("/serviceInstance/{pid}")
    public Order serviceInstanced(@PathVariable("pid") Long pid) {
        log.info("接收到ID为{}的商品下单请求", pid);

        /*
         * 通过 DiscoveryClient 的 getInstances 方法可以获取到指定的服务实例对象（返回值是集合，因为服务会有集群的情况）。
         * 通过服务实例对象，可获取服务的地址和端口
         */
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        // 此处测试只有一个商品微服务，所以直接获取服务列表第一个是没有问题，但如果是集群服务的话，这种调用方式显然有问题
        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getHost(); // 地址
        int port = serviceInstance.getPort(); // 端口
        Product product = restTemplate
                .getForObject(String.format("http://%s:%d/product/%s", host, port, pid), Product.class);

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

    /**
     * 下单请求方法，通过硬编码方式调用商品查询接口
     *
     * @param pid
     * @return
     */
    @GetMapping("/hardcoded/{pid}")
    public Order hardcoded(@PathVariable("pid") Long pid) {
        log.info("接收到ID为{}的商品下单请求", pid);

        Product product = restTemplate.getForObject("http://127.0.0.1:8081/product/" + pid, Product.class);

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
