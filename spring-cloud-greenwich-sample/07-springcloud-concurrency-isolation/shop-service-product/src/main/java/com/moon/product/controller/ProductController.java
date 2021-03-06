package com.moon.product.controller;

import com.moon.entity.Product;
import com.moon.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品控制类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2020-10-8 09:27
 * @description
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        // 睡眠2秒，模拟请求响应慢
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productService.findById(id);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping
    public String save(@RequestBody Product product) {
        productService.save(product);
        return "保存成功";
    }

    @PutMapping
    public String update(@RequestBody Product product) {
        productService.update(product);
        return "修改成功";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "删除成功";
    }

}
