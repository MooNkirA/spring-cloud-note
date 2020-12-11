# spring-cloud-greenwich-sample

## 项目介绍

`spring-cloud-greenwich-sample` 项目是用于学习 Spring Cloud Greenwich版本的基础使用示例项目。

## 项目目录结构

```
spring-cloud-greenwich-sample
 ├── 01-microservice-no-springcloud  # 不使用Spring Cloud的情况下实现微服务架构，分析此方法存在的问题
 ├── 02-springcloud-eureka  # Spring Cloud Eureka 使用示例项目
 ├── 03-springcloud-eureka-cluster  # Spring Cloud Eureka 高可用集群使用示例项目
 ├── 04-springcloud-ribbon  # Spring Cloud Ribbon 负载均衡组件基础使用示例项目
 ├── 05-springcloud-consul  # Spring Cloud Consul 注册中心使用示例项目
 ├── 06-springcloud-feign  # Spring Cloud Feign 服务调用组件使用示例项目
 ├── 07-springcloud-concurrency-isolation  # 基于线程池的形式完成服务的隔离，防止服务雪崩效应
 ├── 08-springcloud-hystrix-resttemplate  # 微服务 RestTemplate 引入 Spring Cloud Hystrix 基础使用示例项目
 ├── 09-springcloud-hystrix-feign  # Spring Cloud Feign 整合 Hystrix 基础使用示例项目
 ├── 10-springcloud-alibaba-sentinel  # Spring Cloud Alibaba Sentinel 基础使用示例项目
 ├── 11-springcloud-zuul  # Spring Cloud Zuul 服务网关基础使用示例项目
 ├── 12-springcloud-gateway  # Spring Cloud Gateway 服务网关基础使用示例项目
 ├── 13-springcloud-gateway-sentinel  # Spring Cloud Gateway 服务网关基于Sentinel的实现限流功能的示例项目
 └── spring-cloud-sample-common # Spring Cloud 示例项目各个模块公共聚合模块
      └── shop-service-common   # 公共模块，用于存放公共的实体类和工具类
 └── reference-technology-demo  # Spring Cloud 涉及的基础技术示例
      └── import-importselector-demo # 使用@Import注解的引入ImportSelector接口的实现类的基础使用示例
```

