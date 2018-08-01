package com.activity.interceptor;

import com.activity.annotation.IgnoreLoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 类描述：
 * 创建人：yyf
 * 创建时间：2018/7/31 0031下午 02:31
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        //TODO 登录验证
        boolean result = false;
        if (result){
            System.out.println("已登录");
            return true;
        }else { //未登录
            if (hasIgnoreLoginInterceptor(handler)) {
                return true;
            }
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private boolean hasIgnoreLoginInterceptor(Object handler) {
        log.info(handler.getClass().getName());
        log.info("是不是HandlerMethod,{}",handler instanceof HandlerMethod);
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            log.info("method:{}",method.getName());
            IgnoreLoginInterceptor annotation = AnnotationUtils.findAnnotation(method, IgnoreLoginInterceptor.class);
            log.info("annotation:{}",annotation);
            if (Objects.nonNull(annotation)) {
                return true;
            }
        }
        return false;
    }
}
