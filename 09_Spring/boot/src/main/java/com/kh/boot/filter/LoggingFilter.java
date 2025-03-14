package com.kh.boot.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
    Interceptor와 AOP는 스프링 내부에서 동작하는 반면, Filter는 스프링MVC외부에서 Servlet에 요청이 도착 전에 동작한다.

    로그를 띄우려면 외부에서 전달받은 Request값을 스프링으로 보내기 전에 한번은 읽어와야하는데,
    서블릿요청의 전첵값은 스트림으로 전달되기 때문에 딱 한번만 읽어올 수 있음.
    Filter에서 요청을 읽어 사용하고 스프링으로 전달하면 에러가 발생함.

    1. 클라이언트가 HTTP요청 보냄
    2. Filter요청을 가로채서 전처리 실행
    3. 다른 Filter 또는 서블릿으로 요청을 전달
    4. 서블릿 실행이 끝나면 Filter가 다시 응답을 가로채서 후처리
    5. 클라이언트로 응답을 반환

    장점
    - 모든 요청에 대해서 처리할 수 있다.(범용적인 적용 가능)

    단점
    - 모든 요청에 대해서 처리하기 때문에 무조건 모든 로직이 동작한다.
    - Sprrig MVC컨트롤러 내부 동작을 알 수 없음
 */

//OncePerRequestFilter
//일반적인 Filter는 여러번 호출될 위험이 있음
//그래서 한요청당 필터를 한번만 실행하여 중복을 방지하는 역할이 더해진 Filter
@Slf4j
//@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        try {
            log.info("request : {}", new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8));
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            log.info("response : {}",new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8));
            responseWrapper.copyBodyToResponse(); // 캐싱된 응답데이터를 실제 HTTP응답으로 다시 전달
        }
    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
////        HttpServletRequest request = (HttpServletRequest)servletRequest;
////
////        //한번 값을 가져오면 본문을 더이상 가져올 수 없음
////        BufferedReader reader = request.getReader();
////        StringBuilder sb = new StringBuilder();
////        String line;
////        while ((line = reader.readLine()) != null) {
////            sb.append(line);
////        }
////
//           //다음으로 실행될 Spring MVC 컨트롤러에서 요청 본문을 읽을 수 없음
////        filterChain.doFilter(servletRequest, servletResponse);
//        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
//        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
//
//        try {
//            filterChain.doFilter(requestWrapper, responseWrapper);
//            log.info("request : {}", requestWrapper.getContentAsString());
//        } finally {
//            log.info("response : {}",new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8));
//            responseWrapper.copyBodyToResponse(); // 캐싱된 응답데이터를 실제 HTTP응답으로 다시 전달
//        }
//    }

}
