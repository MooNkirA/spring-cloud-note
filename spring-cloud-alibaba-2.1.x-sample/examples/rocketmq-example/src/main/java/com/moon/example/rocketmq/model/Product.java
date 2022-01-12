package com.moon.example.rocketmq.model;

import java.math.BigDecimal;
import java.util.StringJoiner;

/**
 * 商品类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-07 10:36
 * @description
 */
public class Product {

    private String id;
    private String productName;
    private BigDecimal price;
    private String productDesc;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("productName='" + productName + "'")
                .add("price=" + price)
                .add("productDesc='" + productDesc + "'")
                .toString();
    }
}
