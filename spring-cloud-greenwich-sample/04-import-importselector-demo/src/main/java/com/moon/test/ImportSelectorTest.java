package com.moon.test;

import com.moon.annotation.EnableUserBean;
import com.moon.bean.User;
import com.moon.config.UserConfiguration;
import com.moon.importselector.UserImportSelector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-10 16:40
 * @description
 */
@EnableUserBean
public class ImportSelectorTest {

    public static void main(String[] args) {
        // 创建注解扫描容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportSelectorTest.class);
        // 从容器获取User实例
        User user = context.getBean(User.class);
        System.out.println(user);
        // UserConfiguration实例有注册到spring容器中
        UserConfiguration config = context.getBean(UserConfiguration.class);
        System.out.println(config);
        // 此处会报错：No qualifying bean of type 'com.moon.importselector.UserImportSelector' available
        // 说明实现了ImportSelector接口或者ImportBeanDefinitionRegistrar接口的类不会被解析成一个Bean注册到容器中
        UserImportSelector importSelector = context.getBean(UserImportSelector.class);
        System.out.println(importSelector);
    }

}
