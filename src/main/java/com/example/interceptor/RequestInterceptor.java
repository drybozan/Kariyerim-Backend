package com.example.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class RequestInterceptor extends HandlerInterceptorAdapter{

    private static final Logger logger = Logger.getLogger(RequestInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        System.out.println(method.getName() + " isimli metodun yurutumunden once");
        logger.info(method.getName() + " isimli metodun yurutumunden once");
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod hm = (HandlerMethod) handler;
        Method method = hm.getMethod();
        System.out.println(method.getName() + " isimli metodun yurutumunden sonra");
        logger.info(method.getName() + " isimli metodun yurutumunden sonra");
        super.postHandle(request, response, handler, modelAndView);
    }
}