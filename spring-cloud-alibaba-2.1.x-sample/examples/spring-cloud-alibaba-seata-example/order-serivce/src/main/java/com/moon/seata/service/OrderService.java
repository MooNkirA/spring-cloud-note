package com.moon.seata.service;

import com.moon.seata.domain.Order;

/**
 * 订单业务接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:14
 * @description
 */
public interface OrderService {

    /**
     * 下单并扣减商品库存，用于测试 seata 分布式事务
     *
     * @param pid
     * @return
     */
    Order createOrder(Long pid);

}
