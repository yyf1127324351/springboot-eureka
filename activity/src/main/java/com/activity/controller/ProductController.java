package com.activity.controller;

import com.activity.annotation.IgnoreLoginInterceptor;
import com.activity.model.dto.ProductDto;
import com.activity.dao.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/30 0030下午 03:46
 */
@RestController
@RequestMapping("product/")
@Slf4j
public class ProductController {

    @Autowired
    ProductMapper productMapper;

    @GetMapping("getProductList")
    public void getProductList(){
        List<ProductDto> productList = productMapper.getProductList();
        log.info("productList:{}",productList);
        System.out.println(productList);
    }
    @GetMapping("getProductDetail")
    @IgnoreLoginInterceptor
    public String getProductDetail(){
        log.info("productDetail:{}","success");
        return  "success";
    }
}
