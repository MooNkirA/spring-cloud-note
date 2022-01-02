package com.moon.product.service;

import com.moon.domain.Product;

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

}
