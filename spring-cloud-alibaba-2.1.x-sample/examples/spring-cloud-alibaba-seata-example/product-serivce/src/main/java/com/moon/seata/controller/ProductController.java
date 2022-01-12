package com.moon.seata.controller;

import com.alibaba.fastjson.JSON;
import com.moon.seata.domain.Product;
import com.moon.seata.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品控制类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:13
 * @description
 */
@RestController
@RequestMapping("product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    // 商品信息查询
    @GetMapping("/{pid}")
    public Product product(@PathVariable("pid") Long pid) {
        log.info("查询ID{}的商品信息", pid);
        Product product = productService.findById(pid);
        log.info("商品信息查询成功,内容为{}", JSON.toJSONString(product));
        return product;
    }

    /**
     * 扣减商品库存
     *
     * @param pid    商品ID
     * @param number 扣减数量
     */
    @GetMapping("reduceInventory")
    public void reduceInventory(Long pid, Integer number) {
        productService.reduceInventory(pid, number);
    }

}
