package com.moon.example.feign.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 测试实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-26 17:12
 * @description
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String city;
    private String email;

}
