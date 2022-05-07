package com.example.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor extends HandlerInterceptorAdapter{

    private static final Logger logger = Logger.getLogger(RequestInterceptor.class);


    public boolean preHandle(HttpServletRequest request,  HttpServletResponse response, Object handler)  throws Exception {

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        return true;
    }


    public void postHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, ModelAndView modelAndView)
            throws Exception {

        long startTime = (Long)request.getAttribute("startTime");

        long endTime = System.currentTimeMillis();

        long executeTime = endTime - startTime;


        modelAndView.addObject("executeTime",executeTime);


        if(logger.isDebugEnabled()){
            logger.debug("[" + handler + "] executeTime : " + executeTime + "ms");
        }
    }
}