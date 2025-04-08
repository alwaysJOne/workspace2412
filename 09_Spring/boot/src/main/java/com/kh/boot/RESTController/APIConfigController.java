package com.kh.boot.RESTController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/config")
public class APIConfigController {

    @Value("${google.login-api.client-id}")
    private String googleClientId;

    @Value("${google.login-api.redirect-url}")
    private String googleRedirectUri;

    @GetMapping(value = "/google/login", produces = "application/json; charset=UTF-8")
    public ConfigResponse getGooleLoginUrl() throws IOException {

        return new ConfigResponse(googleClientId, googleRedirectUri);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ConfigResponse {

        private String googleClientId;
        private String googleRedirectUri;
    }
}
