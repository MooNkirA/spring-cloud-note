package com.moon.order.service.impl;

import com.moon.domain.Product;
import com.moon.order.service.ProductFeignClient;
import org.springframework.stereotype.Component;

/**
 * 商品服务 Feign 调用容错处理类。
 * 要求实现Feign所在接口,并实现里面的方法。
 * 当 feign 调用出现问题的时候，就会进入到当前类中同名方法中
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-04 15:07
 * @description
 */
@Component
public class ProductFeignClientFallBack implements ProductFeignClient {

    @Override
    public Product findById(Long id) {
        // 调用报错时的处理逻辑
        Product product = new Product();
        product.setId(Long.parseLong("-1"));
        product.setProductName("查询产品出错了");
        return product;
    }

}
