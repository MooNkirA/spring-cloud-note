package com.moon.product.service;

import com.moon.entity.Product;

import java.util.List;

/**
 * 商品业务接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-8 09:20
 * @description
 */
public interface ProductService {

    /**
     * 根据id查询
     */
    Product findById(Long id);

    /**
     * 查询全部
     */
    List<Product> findAll();

    /**
     * 保存
     */
    void save(Product product);

    /**
     * 更新
     */
    void update(Product product);

    /**
     * 删除
     */
    void delete(Long id);

}
