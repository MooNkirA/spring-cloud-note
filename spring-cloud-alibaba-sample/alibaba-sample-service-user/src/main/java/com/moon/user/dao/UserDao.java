package com.moon.user.dao;

import com.moon.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户持久接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:15
 * @description
 */
public interface UserDao extends JpaRepository<User, Long> {
}
