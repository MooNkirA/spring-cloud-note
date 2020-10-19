package com.moon.order.feign;

import com.moon.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ProductFeignClient接口实现，此类中实现的方法为相应的降级方法
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-19 08:35
 * @description
 */
// Hystrix组件会在spring容器中查找FeignClient相应的实现类，调用其降级方法，所在需要标识注解注册到spring容器中
@Component
public class ProductFeignClientCallBack implements ProductFeignClient {

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductFeignClientCallBack.class);

    /**
     * 此方法为ProductFeignClient接口中相应方法的降级实现
     *
     * @param id
     * @return
     */
    @Override
    public Product findById(Long id) {
        LOGGER.info("当前下单商品的id是: " + id + "，触发ProductFeignClientCallBack类中熔断的findById降级方法");
        Product product = new Product();
        product.setProductName("feign调用触发熔断降级方法");
        return product;
    }

}
