# spring-cloud-alibaba-2.1.x-sample

## 项目介绍

`spring-cloud-alibaba-2.1.x-sample` 项目是用于学习 Spring Cloud Alibaba 2.1.x 版本的基础使用示例项目。

## 项目目录结构

```
spring-cloud-alibaba-2.1.x-sample
 ├── api-gateway  # 网关服务，使用 Spring Cloud Gateway
 ├── document  # 项目相关文档、数据库脚本
 ├── examples  # Spring Cloud Alibaba 相关基础示例
 	  ├── alibaba-cloud-sms-example # alibaba 短信服务示例
 	  ├── rocketmq-example # RockMQ 消息中件间示例
 	  └── spring-cloud-alibaba-seata-example # Alibaba Seata 0.9.0服务端+0.7.1客户端的基础使用示例
 ├── modules-common  # Spring Cloud Alibaba 综合示例的公共模块，用于存放公共的实体类和工具类
 ├── service-order  # Spring Cloud Alibaba 综合示例的订单微服务，主要以服务消费调用方使用
 ├── service-product  # Spring Cloud Alibaba 综合示例的商品微服务，主要以服务提供方使用
 ├── service-user  # Spring Cloud Alibaba 综合示例的用户微服务，使用消息中间件RocketMQ时会被调用
```

