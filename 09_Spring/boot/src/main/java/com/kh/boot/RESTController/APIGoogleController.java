package com.kh.boot.RESTController;

import com.google.api.services.drive.model.File;
import com.kh.boot.domain.vo.Form;
import com.kh.boot.service.GoogleAPiService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/google")
public class APIGoogleController {

    private final GoogleAPiService googleAPiService;

    @GetMapping("/forms")
    public List<File> listGoogleForms(HttpSession session) {
        String accessToken = (String)session.getAttribute("access_token");
        System.out.println(accessToken);
        List<File> list = googleAPiService.getGoogleForms(accessToken);

        System.out.println("list : " + list);
        return list;
    }
}
