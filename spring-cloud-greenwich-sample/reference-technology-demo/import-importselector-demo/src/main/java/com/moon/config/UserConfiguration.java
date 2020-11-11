package com.moon.config;

import com.moon.bean.User;
import org.springframework.context.annotation.Bean;

/**
 * 配置类，注意：此类不标识@Component、@Service、@Repository、@Controller等注解，
 * spring扫描的时候并不会装载该类，待使用@Import注解引入一个ImportSelector接口实现类，在实现类中处理注册到容器中
 * 注意事项：实现了ImportSelector接口的类不会被解析成一个Bean注册到容器中，只会将里面标识的@Bean注解的方法创建实例注册到容器
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-10 16:29
 * @description
 */
public class UserConfiguration {

    /* 创建User实例 */
    @Bean
    public User getUser() {
        User user = new User();
        user.setAge(12);
        user.setName("石原里美");
        return user;
    }

}
