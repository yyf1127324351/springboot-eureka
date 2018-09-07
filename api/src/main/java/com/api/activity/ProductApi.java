package com.api.activity;

import com.api.activity.response.ProductResp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/9/7 0007上午 10:55
 */
@FeignClient(value = "activity",path = "/product")
public interface ProductApi {
    @RequestMapping(value = "/getProductList",method = RequestMethod.GET)
    List<ProductResp> getProductList();
}
