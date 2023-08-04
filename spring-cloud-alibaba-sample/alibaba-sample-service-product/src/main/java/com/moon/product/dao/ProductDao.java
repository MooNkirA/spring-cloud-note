package com.moon.product.dao;

import com.moon.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品持久接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:15
 * @description
 */
public interface ProductDao extends JpaRepository<Product, Long> {
}
