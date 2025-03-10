package com.kh.boot.common.controller;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;

//Bean에 class등록하는 방법으로 @Component을 클래스에 부여한다.
//@Controller -> @Component + Controller객체가 가질 수 있는 예외처리등의 기능을 포함하는 어노테이션
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {

        return "index";
    }
}
