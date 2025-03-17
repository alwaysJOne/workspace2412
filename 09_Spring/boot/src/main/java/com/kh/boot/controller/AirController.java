package com.kh.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AirController {
    /*
        @Value
        springboot에서 @Value을 사용하면 application.properties에 지정한 값을 쉽게 가져올 수 있음.
     */
    @Value("${open.api.key.air}")
    private String airApiKey;

    @GetMapping("/main.air")
    public String main(Model model) {

        //클라이언트에서 직접 요청을 하기위해 airServiceKey를 전달
        model.addAttribute("airServiceKey", airApiKey);
        return "air/airInfo";
    }
}
