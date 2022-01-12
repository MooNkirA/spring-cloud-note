package com.moon.product.service.impl;

import com.moon.domain.Product;
import com.moon.product.dao.ProductDao;
import com.moon.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品业务实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:14
 * @description
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product findById(Long pid) {
        return productDao.findById(pid).orElse(null);
    }

}
