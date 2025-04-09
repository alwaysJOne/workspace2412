package com.kh.boot.RESTController;

import com.google.api.services.drive.model.File;
import com.kh.boot.service.GoogleAPIService;
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

    private final GoogleAPIService googleAPIService;

    @GetMapping("/forms")
    public List<File> listGoogleForms(HttpSession session) {
        try {
            return googleAPIService.getGoogleForms((String)session.getAttribute("googleAccessToken"));
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

}
