package com.moon.product.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Eureka 相关测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-9 16:04
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaTest {

    @Autowired
    private DiscoveryClient discoveryClient;

    /* Eureka元数据 */
    @Test
    public void eurekaMetadataTest() {
        // 调用discoveryClient的getInstances方法，根据微服务名称从注册中心获取相关的元数据信息
        List<ServiceInstance> instances = discoveryClient.getInstances("shop-service-product");
        // 循环所有元数据
        for (ServiceInstance instance : instances) {
            System.out.println("hostname: " + instance.getHost());
            System.out.println("instanceId: " + instance.getInstanceId());
            System.out.println("port: " + instance.getPort());
            System.out.println("scheme: " + instance.getScheme());
            System.out.println("serviceId: " + instance.getServiceId());
            System.out.println("uri: " + instance.getUri());
            System.out.println("================== 分隔线 =====================");
            // 循环
            for (Map.Entry<String, String> entry : instance.getMetadata().entrySet()) {
                System.out.println("key: " + entry.getKey() + " === value: " + entry.getValue());
            }
            System.out.println("================== 分隔线 =====================");
        }
    }

}
