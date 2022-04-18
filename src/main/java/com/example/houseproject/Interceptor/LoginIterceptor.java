package com.example.houseproject.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object o = request.getSession().getAttribute("username");
        if(o==null){
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
