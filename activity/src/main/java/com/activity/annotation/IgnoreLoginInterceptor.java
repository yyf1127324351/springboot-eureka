package com.activity.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2018/7/31 0031.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreLoginInterceptor {
}
