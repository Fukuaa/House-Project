package com.example.houseproject.Config;

import com.example.houseproject.Interceptor.LoginIterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class Loginconfig implements WebMvcConfigurer {
    @Value("${app.upload-dir:uploads}")
    private String uploadDir;

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor interceptor = new LoginIterceptor();
        //注册拦截器
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/tologin")
                .excludePathPatterns("/goZhuChe")
                .excludePathPatterns("/zhuChe")
                .excludePathPatterns("/error")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/uploads/**")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.png")
                .excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/**/*.jpeg")
                .excludePathPatterns("/**/*.gif")
                .excludePathPatterns("/**/*.svg");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploadPath = Paths.get(uploadDir);
        if (!uploadPath.isAbsolute()) {
            uploadPath = Paths.get(System.getProperty("user.dir")).resolve(uploadPath);
        }
        String location = "file:" + uploadPath.toAbsolutePath().normalize().toString().replace("\\", "/") + "/";
        registry.addResourceHandler("/uploads/**").addResourceLocations(location);
    }
}
