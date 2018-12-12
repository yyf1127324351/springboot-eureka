package com.activity.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/11/16 0016下午 11:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    DianXiaoDataService dianXiaoDataService;

    @org.junit.Test
    public void test() {
        dianXiaoDataService.syncDianXiaoNoPay();
    }
}
