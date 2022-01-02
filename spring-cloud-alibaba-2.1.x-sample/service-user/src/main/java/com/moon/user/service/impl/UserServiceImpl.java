package com.moon.user.service.impl;

import com.moon.domain.User;
import com.moon.user.dao.UserDao;
import com.moon.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 19:14
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Long id) {
        return userDao.findById(id).orElse(null);
    }
}

