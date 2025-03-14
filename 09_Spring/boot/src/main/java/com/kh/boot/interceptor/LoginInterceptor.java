package com.kh.boot.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession sessionn = request.getSession();

        if(sessionn.getAttribute("loginUser") != null) {
            return true; // 계속 실행
        } else {
            sessionn.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
            response.sendRedirect("/");
            return false;
        }
    }
}
