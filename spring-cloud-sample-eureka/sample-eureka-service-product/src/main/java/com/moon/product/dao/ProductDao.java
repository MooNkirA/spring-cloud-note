package com.moon.product.dao;

import com.moon.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 商品持久层接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-8 09:16
 * @description
 */
// 继承JPA相关接口，其中JpaSpecificationExecutor是用于复杂动态查询
public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
