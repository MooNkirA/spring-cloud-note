package com.moon.order.dao;

import com.moon.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单持久接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:15
 * @description
 */
public interface OrderDao extends JpaRepository<Order, Long> {
}
