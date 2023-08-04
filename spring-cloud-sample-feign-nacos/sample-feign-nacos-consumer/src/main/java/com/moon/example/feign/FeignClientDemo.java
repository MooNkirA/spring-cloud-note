package com.moon.example.feign;

import com.moon.example.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * FeignClient 接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-03-23 22:47
 * @description
 */
// @FeignClient 注解，用于标识当前接口为Feign调用微服务的核心接口
@FeignClient(name = "service-provider")
// 通过 configuration 指定配置类，此配置类中设置了 Feigin client 调用服务的日志级别
// @FeignClient(name = "service-provider", configuration = FeignClientConfig.class)
public interface FeignClientDemo {

    @GetMapping("/hello")
    String hello(@RequestParam("name") String name);

    // 测试 Feign 多参数传递问题
    @GetMapping("/user")
    User getUser(User user);

    // Feign 多参数传递问题解决方案1：参数注解 @SpringQueryMap
    @GetMapping("/user1")
    User getUserBySpringQueryMap(@SpringQueryMap User user);

    // Feign 多参数传递问题解决方案2：使用 @RequestParam 注解指定多个独立参数
    @GetMapping("/user2")
    User getUserByParam(@RequestParam("id") Integer id,
                        @RequestParam("name") String name,
                        @RequestParam("city") String city,
                        @RequestParam("email") String email);

    // Feign 调用复杂形式参数接口测试
    @RequestMapping(path = "/demo_complex/{id}", method = RequestMethod.POST)
    String demo_complex(@PathVariable("id") String id,
                        @RequestBody Map<String, Object> map,
                        @RequestParam("name") String name);

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String handleFileUpload(@RequestPart(value = "file") MultipartFile file);
}
