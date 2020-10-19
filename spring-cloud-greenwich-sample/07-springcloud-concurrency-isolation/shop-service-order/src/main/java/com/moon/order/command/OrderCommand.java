package com.moon.order.command;

import com.moon.entity.Product;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.springframework.web.client.RestTemplate;

/**
 * HystrixCommand的原生实现方式，对服务进行服务降级限流
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-18 10:50
 * @description
 */
public class OrderCommand extends HystrixCommand<Product> {

    private RestTemplate restTemplate;

    private Long id;

    public OrderCommand(RestTemplate restTemplate, Long id) {
        super(setter());
        this.restTemplate = restTemplate;
        this.id = id;
    }

    private static Setter setter() {

        // 服务分组
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("order_product");
        // 服务标识
        HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("product");
        // 线程池名称
        HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("order_product_pool");
        /*
         * 线程池配置
         *     withCoreSize : 线程池大小
         *     withKeepAliveTimeMinutes : 线程存活时间15秒
         *     withQueueSizeRejectionThreshold : 队列等待的阈值为100,超过100执行拒绝策略
         */
        // 注：测试案例设置了tomcat最大线程数为10，所以这里设置线程池大小为5，实现此接口的线程数量控制。
        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter().withCoreSize(5)
                .withKeepAliveTimeMinutes(15).withQueueSizeRejectionThreshold(100);

        // 命令属性配置Hystrix 开启超时
        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                // 采用线程池方式实现服务隔离
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                // 禁止
                .withExecutionTimeoutEnabled(false);
        return HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey).andThreadPoolKey(threadPoolKey)
                .andThreadPoolPropertiesDefaults(threadPoolProperties).andCommandPropertiesDefaults(commandProperties);

    }

    @Override
    protected Product run() throws Exception {
        return restTemplate.getForObject("http://127.0.0.1:9001/product/" + id, Product.class);
    }

    /**
     * 服务降级方法
     *
     * @return
     */
    @Override
    protected Product getFallback() {
        Product product = new Product();
        product.setProductName("服务降级方法返回的数据");
        return product;
    }
}
