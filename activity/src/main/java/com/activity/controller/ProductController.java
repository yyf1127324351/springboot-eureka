package com.activity.controller;

import com.activity.annotation.IgnoreLoginInterceptor;
import com.activity.model.dto.ProductDto;
import com.activity.dao.ProductMapper;
import com.activity.utils.BaseResponse;
import com.activity.utils.Pager;
import com.api.activity.ProductApi;
import com.api.activity.response.ProductResp;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public BaseResponse getProductDetail(){
        Map<String, Object> param = new HashMap();
        param.put("pageSize", 3);
        param.put("start", 1);
        Pager<ProductDto> pageInfo = new Pager(1,3);
        List<ProductDto> productList = productMapper.getProductLists(param);
        pageInfo.setList(productList);
        Long count = productMapper.selectCount(param);
        pageInfo.setTotalCount(count.intValue());
        log.info("productDetail:{}",productList);
        return new BaseResponse(pageInfo);
    }
}
