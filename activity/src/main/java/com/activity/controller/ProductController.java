package com.activity.controller;

import com.activity.annotation.IgnoreLoginInterceptor;
import com.activity.model.dto.ProductDto;
import com.activity.dao.ProductMapper;
import com.api.activity.ProductApi;
import com.api.activity.response.ProductResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/30 0030下午 03:46
 */
@RestController
@RequestMapping("product/")
@Slf4j
public class ProductController implements ProductApi {

    @Autowired
    ProductMapper productMapper;

    @IgnoreLoginInterceptor
    public List<ProductResp> getProductList(){
        List<ProductDto> productList = productMapper.getProductList();
        List<ProductResp> productRespList = new ArrayList<>();
        for (ProductDto productDto : productList){
            ProductResp  productResp = new ProductResp();
            BeanUtils.copyProperties(productDto,productResp);
            productRespList.add(productResp);
        }

        log.info("productList:{}",productList);
        return productRespList;
    }
    @GetMapping("getProductDetail")
    @IgnoreLoginInterceptor
    public String getProductDetail(){
        log.info("productDetail:{}","success");
        return  "success";
    }
}
