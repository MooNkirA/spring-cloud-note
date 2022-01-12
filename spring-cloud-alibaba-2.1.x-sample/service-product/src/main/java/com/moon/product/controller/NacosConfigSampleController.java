package com.moon.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Nacos Config 动态配置测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2022-01-08 16:39
 * @description
 */
@RestController
@RequestMapping("nacos-config")
@RefreshScope // Spring Cloud 原生注解，实现配置文件的动态加载。只需要在需要动态读取配置的类上添加此注解即可。
public class NacosConfigSampleController implements EnvironmentAware {

    @Value("${user.name}") // 动态注入配置中心相应的配置项
    private String userName;

    @Value("${user.age}")
    private int age;

    private Environment environment;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    /**
     * 通过 @Value + @RefreshScope 注解动态获取配置项的值
     *
     * @return
     */
    @GetMapping("value-annotation")
    public String getByValueAnnotation() {
        return String.format("通过@Value注解获取配置值，name: %s, age: %d", userName, age);
    }

    /**
     * 通过 ConfigurableApplicationContext 对象动态获取配置项的值
     *
     * @return
     */
    @GetMapping("configurableApplicationContext")
    public String getByConfigurableApplicationContext() {
        String userName = applicationContext.getEnvironment().getProperty("user.name");
        String age = applicationContext.getEnvironment().getProperty("user.age");
        return String.format("通过 ConfigurableApplicationContext.getEnvironment() 获取 Environment对象，获取配置值，name: %s, age: %s", userName, age);
    }

    /**
     * 配置 Environment 对象获取配置项的值
     *
     * @return
     */
    @GetMapping("environment")
    public String getByEnvironment() {
        String userName = environment.getProperty("user.name");
        String age = environment.getProperty("user.age");
        return String.format("通过 Environment 对象获取配置值，name: %s, age: %s", userName, age);
    }

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
