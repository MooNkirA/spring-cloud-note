package com.moon.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 商品实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 17:02
 * @description
 */
@Entity(name = "tb_product")
@Data
public class Product {

    @Id // 声明当前私有属性为数据库表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 配置主键的生成策略
    private Long id;
    @Column(name = "product_name")
    private String productName;
    private Integer status;
    private BigDecimal price;
    @Column(name = "product_desc")
    private String productDesc;
    private String caption;
    private Integer inventory;

}
