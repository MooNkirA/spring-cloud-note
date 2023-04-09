package com.moon.example.feign;

import com.moon.example.feign.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-24 23:15
 * @description
 */
@RestController
public class ComsumerTestController {

    @Autowired
    private FeignClientDemo feignClientDemo;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return feignClientDemo.hello(name);
    }

    @GetMapping("/user")
    public User getUser(Integer id, String name, String city, String email) {
        User user = User.builder().id(id).name(name).city(city).email(email).build();
        return feignClientDemo.getUser(user);
    }

    @GetMapping("/user1")
    public User getUser1(Integer id, String name, String city, String email) {
        User user = User.builder().id(id).name(name).city(city).email(email).build();
        return feignClientDemo.getUserBySpringQueryMap(user);
    }

    @GetMapping("/user2")
    public User getUser2(Integer id, String name, String city, String email) {
        return feignClientDemo.getUserByParam(id, name, city, email);
    }

    @PostMapping(value = "/upload")
    public String fileUpload(MultipartFile file) throws Exception {
        return feignClientDemo.handleFileUpload(file);
    }
}
