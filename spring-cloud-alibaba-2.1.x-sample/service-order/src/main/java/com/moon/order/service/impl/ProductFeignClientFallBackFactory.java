package com.moon.order.service.impl;

import com.moon.domain.Product;
import com.moon.order.service.ProductFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 商品服务 Feign 调用容错处理类。
 * 与直接实现 Feign 接口的方式不同的地点在于，在此接口的 create 方法，可以获取到容错时发生的异常的信息。
 * `FallbackFactory<T>` 的泛型为容错的 Feign 的接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-04 15:32
 * @description
 */
@Slf4j
@Service
public class ProductFeignClientFallBackFactory implements FallbackFactory<ProductFeignClient> {

    /**
     * Returns an instance of the fallback appropriate for the given cause.
     * 此方法返回相应的 Feign 接口实现类。所以直接创建 Feign 接口的实现类，在重写里面所有方法，
     * 执行的效果与容错类直接实现 Feign 接口一样
     *
     * @param cause 这就是 fegin 在调用过程中产生异常
     */
    @Override
    public ProductFeignClient create(Throwable cause) {
        return new ProductFeignClient() {
            @Override
            public Product findById(Long id) {
                // 此处就可以获取到异常发生的具体信息，做相应的处理和分析
                log.error("ProductFeignClientFallBackFactory 容错获取到的异常信息是：{}", cause);
                // 调用报错时的处理逻辑
                Product product = new Product();
                product.setId(Long.parseLong("-1"));
                product.setProductName("查询产品出错了");
                return product;
            }
        };
    }

}
