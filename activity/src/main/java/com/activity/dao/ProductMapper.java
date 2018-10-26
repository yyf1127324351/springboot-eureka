package com.activity.dao;

import com.activity.model.dto.ProductDto;
import com.activity.utils.Pager;

import java.util.List;
import java.util.Map;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/30 0030下午 03:46
 */
public interface ProductMapper {
    List<ProductDto> getProductList();

    List<ProductDto> getProductLists(Map<String, Object> param);

    Long selectCount(Map<String, Object> param);
}
