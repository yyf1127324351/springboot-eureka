package com.apple.controller;

import com.api.activity.ProductApi;
import com.api.activity.response.ProductResp;
import com.apple.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/9/4 0004下午 04:22
 */
@RestController
@RequestMapping("apple/")
public class AppleController {
    @Autowired
    ProductApi productApi;

    @GetMapping("get")
    public BaseResponse get(){
        List<ProductResp> productList = productApi.getProductList();
        return new BaseResponse(productList);
    }

}
