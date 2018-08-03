package com.activity.config;

import com.activity.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Objects;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/31 0031下午 03:09
 */
@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    LoginInterceptor loginInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        log.info("loginInterceptor是否存在:{}", Objects.nonNull(loginInterceptor));
        if(Objects.nonNull(loginInterceptor)) {
            log.info("开启 loginInterceptor");
            //不需要拦截的请求地址
            registry.addInterceptor(loginInterceptor).excludePathPatterns(new String[] {
                    "/product/productList"
            });
        }
        //需要拦截的请求地址
        registry.addInterceptor(loginInterceptor).addPathPatterns(new String[]{"/service/index/loan-order"});
        super.addInterceptors(registry);

    }
}
