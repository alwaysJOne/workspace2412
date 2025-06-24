package com.kh.login.controller;

import com.kh.login.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestEcxeptionController {

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id) {
        //Member m = service.getUser(id);
        //if(m == null)
        if (id <= 0) {
            throw new UserNotFoundException();
        }

        return "사용자 정보" + id;
    }

    @GetMapping("/illegal")
    public String testIllegalArgument(@RequestParam Integer number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다. : " + number);
        }

        return "정상응답 : " + number;
    }
}
