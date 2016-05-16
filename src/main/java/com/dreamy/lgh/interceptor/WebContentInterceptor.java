package com.dreamy.lgh.interceptor;


import com.dreamy.lgh.controllers.BaseController;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebContentInterceptor implements HandlerInterceptor, ApplicationContextAware {



    protected ApplicationContext applicationContext;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg) throws Exception {
        if (arg != null && arg instanceof BaseController) {

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

}
