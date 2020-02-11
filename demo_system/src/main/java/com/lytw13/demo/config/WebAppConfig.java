//package com.lytw13.demo.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//public class WebAppConfig implements WebMvcConfigurer {
//    //实现拦截器 要拦截的路径以及不拦截的路径
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RememberMeInterceptor()).addPathPatterns("/**").excludePathPatterns("/loginPage","/login");
//    }
//}