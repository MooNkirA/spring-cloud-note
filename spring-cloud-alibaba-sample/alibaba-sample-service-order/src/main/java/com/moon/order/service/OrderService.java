package com.moon.order.service;

import com.moon.domain.Order;

/**
 * 订单业务接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:14
 * @description
 */
public interface OrderService {

    void createOrder(Order order);
    
}
