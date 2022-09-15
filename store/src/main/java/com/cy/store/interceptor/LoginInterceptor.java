package com.cy.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                //获得session中的用户信息
                HttpSession session = request.getSession();
                Object username = session.getAttribute("username");
                //判断是否为空 空表示未登入则重定向到登入页面
                if (username == null){
                        response.sendRedirect("/web/login.html");
                        return false;
                }
                return true;
        }
}
