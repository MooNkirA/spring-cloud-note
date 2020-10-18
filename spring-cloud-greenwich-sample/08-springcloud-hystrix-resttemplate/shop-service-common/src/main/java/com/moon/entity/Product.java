package com.moon.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 商品实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-8 09:08
 * @description
 */
@Data
@Entity // 标识为jpa实体类
@Table(name = "tb_product") // 建立实体类和表的映射关系
public class Product {

    @Id // 声明当前私有属性为数据库表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 配置主键的生成策略
    private Long id;
    private String productName;
    private Integer status;
    private BigDecimal price;
    private String productDesc;
    private String caption;
    private Integer inventory;

}
