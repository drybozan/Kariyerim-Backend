package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


import javax.servlet.Registration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class ApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigApplicationContext context = getContext();
        servletContext.addListener(new ContextLoaderListener((WebApplicationContext) context));
        Registration.Dynamic dispatcherServlet = servletContext.addServlet("DispatcherServlet",new DispatcherServlet(context));
        ((ServletRegistration.Dynamic) dispatcherServlet).setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
        
    }

    private AnnotationConfigApplicationContext getContext() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.setConfigLocation("");
        return context;
    }
}
