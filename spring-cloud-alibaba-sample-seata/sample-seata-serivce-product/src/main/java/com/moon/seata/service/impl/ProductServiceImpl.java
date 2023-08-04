package com.moon.seata.service.impl;

import com.moon.seata.dao.ProductDao;
import com.moon.seata.domain.Product;
import com.moon.seata.service.ProductService;
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

    // 扣减商品库存，简单实现
    @Transactional
    @Override
    public void reduceInventory(Long pid, Integer number) {
        log.info("开始扣减库存.....");
        // 根据 id 查询商品（案例为了方便简单，省略校验）
        Product product = productDao.findById(pid).get();

        // 扣减库存
        product.setInventory(product.getInventory() - number);

        // 保存
        productDao.save(product);
        int i = 1 / 0; // 模拟异常
        log.info("扣减库存成功.....");
    }

}
