package com.api.activity.response;

import lombok.Data;

import java.util.Date;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/9/7 0007下午 02:35
 */
@Data
public class ProductResp {
    private Long id;
    private String productName;
    private Date beginDate;
    private Date endDate;
    private String activityName;
    private Integer activityId;
    private Integer flag;
}
