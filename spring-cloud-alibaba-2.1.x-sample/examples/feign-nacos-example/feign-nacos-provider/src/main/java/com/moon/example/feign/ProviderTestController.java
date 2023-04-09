package com.moon.example.feign;

import com.moon.example.feign.pojo.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.StringJoiner;

/**
 * 测试接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-22 22:23
 * @description
 */
@RestController
public class ProviderTestController {

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hello " + name + "!";
    }

    @GetMapping("/user")
    public User getUser(User user) {
        return User.builder().id(1).name(user.getName()).city(user.getCity()).email("moon@moon.com").build();
    }

    @GetMapping("/user1")
    public User getUser1(User user) {
        return User.builder().id(2).name(user.getName()).city(user.getCity()).email("zero@moon.com").build();
    }

    @GetMapping("/user2")
    public User getUser2(User user) {
        return User.builder().id(3).name(user.getName()).city(user.getCity()).email("qiulan@moon.com").build();
    }

    // 复杂形式参数处理测试
    @PostMapping("/demo_complex/{id}")
    public String demo_complex(@PathVariable("id") String id,
                               @RequestBody Map<String, Object> map) {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            joiner.add(entry.getKey() + ":" + entry.getValue());
        }
        return "PathVariable id :" + id + "\nRequestBody map: " + joiner.toString();
    }

    // 文件上传测试接口
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(MultipartFile file) throws Exception {
        // 测试只作简单的返回文件名称
        return file.getOriginalFilename();
    }
}
