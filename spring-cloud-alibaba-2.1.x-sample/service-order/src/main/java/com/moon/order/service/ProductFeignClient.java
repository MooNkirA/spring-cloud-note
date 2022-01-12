package com.moon.order.service;

import com.moon.domain.Product;
import com.moon.order.service.impl.ProductFeignClientFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品服务 Feign 调用接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-02 13:25
 * @description
 */
/*
 * @FeignClient 注解，用于标识当前接口为Feign调用微服务的核心接口
 *  value/name属性：指定需要调用的服务提供者注册的名称
 *  fallback属性：指定当调用出现问题之后，要执行哪个容错类中的同名方法的备用逻辑
 */
// @FeignClient(value = "service-product", fallback = ProductFeignClientFallBack.class)
@FeignClient(value = "service-product", fallbackFactory = ProductFeignClientFallBackFactory.class)
// 注意：fallback 和 fallbackFactory 只能使用其中一种方式
public interface ProductFeignClient {

    /*
     * 创建需要调用的微服务接口方法，SpringCloud 对 Feign 进行了增强兼容了 SpringMVC 的注解
     * @FeignClient 的 value值 + @RequestMapping 的 value 值，相当于服务的请求地址："http://service-product/product/" + pid
     *  在使用的两个注意点：
     *  1. FeignClient 接口有参数时，必须在参数加@PathVariable("XXX")和@RequestParam("XXX")注解，并且必须要指定对应的参数值（原来SpringMVC是可以省略）
     *  2. feignClient 返回值为复杂对象时，其对象类型必须有无参构造函数
     *  3. 方法的名称不需要与被调用的服务接口名称一致
     */
    @GetMapping("/product/{pid}")
    Product findById(@PathVariable("pid") Long id);

}
