package com.moon.product.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * JPA的配置类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:56
 * @description
 */
@Configuration
/*
 * 使用 @EntityScan 注解指定 JPA 的 Entity 扫描包路径。
 * 因为示例项目启动类默认扫描的包路径不包含实体类所在的包路径，
 * 如果这里不指定JPA扫描实体类的包路径，启动会报错，找不到 JpaRepository 接口泛型指定的实体类：
 * Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: Not a managed type: class com.moon.domain.User
 */
@EntityScan("com.moon.domain")
public class JpaConfig {
}
