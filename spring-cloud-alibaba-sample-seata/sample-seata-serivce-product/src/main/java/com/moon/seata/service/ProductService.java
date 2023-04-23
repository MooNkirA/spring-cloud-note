package com.moon.seata.service;

import com.moon.seata.domain.Product;

/**
 * 商品业务接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:14
 * @description
 */
public interface ProductService {

    Product findById(Long pid);

    void reduceInventory(Long pid, Integer number);
}
