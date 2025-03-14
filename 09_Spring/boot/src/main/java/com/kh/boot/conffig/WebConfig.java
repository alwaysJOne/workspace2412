package com.kh.boot.conffig;


import com.kh.boot.interceptor.LogginInterceptor;
import com.kh.boot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LogginInterceptor())
//                .addPathPatterns("/api/**")
//                .excludePathPatterns("/static/**", "/error/**");

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/myPage.me")
                .excludePathPatterns("/static/**", "/error/**");
    }
}
