package com.moon.annotation;

import com.moon.importselector.UserImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，使用@Import注解引入ImportSelector接口实现类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-10 16:33
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Import(UserImportSelector.class)
public @interface EnableUserBean {
}
