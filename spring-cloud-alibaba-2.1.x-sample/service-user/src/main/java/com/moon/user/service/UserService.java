package com.moon.user.service;

import com.moon.domain.User;

/**
 * 用户业务接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:14
 * @description
 */
public interface UserService {

    User findById(Long id);

}
