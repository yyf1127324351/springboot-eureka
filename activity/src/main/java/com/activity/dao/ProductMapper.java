package com.activity.dao;

import com.activity.model.dto.ProductDto;

import java.util.List;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/30 0030下午 03:46
 */
public interface ProductMapper {
    List<ProductDto> getProductList();
}
