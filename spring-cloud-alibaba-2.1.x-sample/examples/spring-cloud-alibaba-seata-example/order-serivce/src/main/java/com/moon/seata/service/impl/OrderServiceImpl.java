package com.moon.seata.service.impl;

import com.alibaba.fastjson.JSON;
import com.moon.seata.dao.OrderDao;
import com.moon.seata.domain.Order;
import com.moon.seata.domain.Product;
import com.moon.seata.service.OrderService;
import com.moon.seata.service.ProductFeignClient;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单业务实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:14
 * @description
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductFeignClient productFeignClient;

    /**
     * 下单并扣减商品库存，用于测试 seata 分布式事务
     *
     * @param pid
     * @return
     */
    @Override
    // Seata 实现全局事务，只需要在业务的发起方的方法上使用 `@GlobalTransactional` 注解，即可开启全局事务，
    // Seata 会将事务的 xid 通过拦截器添加到调用其他服务的请求中，实现分布式事务。
    @GlobalTransactional
    public Order createOrder(Long pid) {
        log.info("接收到ID为{}的商品下单请求", pid);

        /* 通过 FeignClient 接口调用本地方法的方式，实现服务的调用。 */
        Product product = productFeignClient.findById(pid);
        log.info("查询到id为{}商品的信息，内容是:{}", pid, JSON.toJSONString(product));

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
            orderDao.save(order);

            // 调用商品服务接口，扣减库存
            productFeignClient.reduceInventory(pid, order.getNumber());
        }

        return order;
    }

}
