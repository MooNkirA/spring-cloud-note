package com.moon.seata.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 17:02
 * @description
 */
@Entity(name = "tb_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // 订单id
    @Column(name = "user_id")
    private Long userId;        // 用户id
    @Column(name = "product_id")
    private Long productId;     // 商品id
    private Integer number;     // 数量
    private BigDecimal price;   // 单价
    private BigDecimal amount;  // 总额
    @Column(name = "product_name")
    private String productName; // 商品名
    private String username;    // 用户名

}
