package com.example.houseproject.Config;

import com.example.houseproject.Interceptor.LoginIterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Loginconfig implements WebMvcConfigurer {

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginIterceptor();
        //注册拦截器
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login");
    }
}
