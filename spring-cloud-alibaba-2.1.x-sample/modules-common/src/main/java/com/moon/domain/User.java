package com.moon.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 用户实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-12-31 17:02
 * @description
 */
@Entity(name = "tb_user") // 实体类跟数据表的对应
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 数据库自增
    private Long id;         // 主键
    private String username;    // 用户名
    private String password;    // 密码
    private String age;         // 年龄
    private BigDecimal balance; // 余额
    private String address;     // 手机号

}
