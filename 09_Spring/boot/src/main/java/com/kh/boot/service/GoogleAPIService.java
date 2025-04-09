package com.kh.boot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class GoogleAPIService {
    @Value("${google.login-api.client-id}")
    private String googleClientId;

    @Value("${google.login-api.redirect-url}")
    private String googleRedirectUri;

    @Value("${google.login-api.client-secret}")
    private String googleClientSecret;

    // 구글 OAuth2 인증 코드로 토큰 요청을 처리하는 별도의 메서드
    public Map<String, String> requestMemberInfo(String code) throws IOException {
        String tokenResponse = requestGoogleToken(code);

        String accessToken = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(tokenResponse);
            // 액세스 토큰 추출
            accessToken = jsonNode.get("access_token").asText();
        }catch (Exception e){
            e.printStackTrace();
        }

        // 액세스 토큰을 사용하여 사용자 정보 가져오기
        String userInfo = getUserInfo(accessToken);

        String memberId = extractMemberId(userInfo);

        Map<String, String> result = new HashMap<>();
        result.put("memberId", memberId);
        result.put("accessToken", accessToken);
        return result;
    }

    private String extractMemberId(String userInfo) {

        // JSON 파싱하여 UserInfo 객체로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(userInfo);

            // JsonNode에서 각 필드를 추출하여 UserInfo 객체에 매핑
            String email = jsonNode.get("email").asText();


            // UserInfo 객체 생성 및 반환
            return email;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 구글 OAuth2 인증 코드로 토큰 요청을 처리하는 별도의 메서드
    private String requestGoogleToken(String code) throws IOException {
        String url = "https://oauth2.googleapis.com/token";

        // 파라미터 설정
        String params = "code=" + URLEncoder.encode(code, "UTF-8") +
                "&client_id=" + URLEncoder.encode(googleClientId, "UTF-8") +
                "&client_secret=" + URLEncoder.encode(googleClientSecret, "UTF-8") +
                "&redirect_uri=" + URLEncoder.encode(googleRedirectUri, "UTF-8") +
                "&grant_type=authorization_code";

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);

        // HTTP POST 요청 보내기
        HttpEntity<String> entity = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();

        // 구글 토큰 엔드포인트로 POST 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        String req = response.getBody();

        // 응답 결과 반환
        return req;
    }

    // 구글 사용자 정보를 가져오는 메서드
    private String getUserInfo(String accessToken) {
        // 구글 사용자 정보 API URL
        String url = "https://www.googleapis.com/oauth2/v3/userinfo";

        // HTTP 헤더 설정 (Authorization 헤더에 access_token 포함)
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        // 요청 본문은 필요 없으므로 HttpEntity는 헤더만 포함
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        // GET 요청으로 사용자 정보 가져오기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // 응답 본문 반환
        return response.getBody();
    }

    public List<File> getGoogleForms(String accessToken) throws IOException, GeneralSecurityException {
        FileList result = getDriveService(accessToken).files().list()
                                      .setQ("mimeType='application/vnd.google-apps.form'")
                                      .setSpaces("drive")
                                      .setFields("files(id, name, createdTime)")
                                      .execute();

        System.out.println(result);

        return result.getFiles();
    }

    // access_token을 사용하여 GoogleDriveService 초기화
    private Drive getDriveService(String accessToken) throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        final JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // access_token으로 GoogleCredentials 생성
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setClientSecrets(googleClientId, googleClientSecret)  // ← 반드시 clientId, clientSecret 필요
                .build()
                .setAccessToken(accessToken);

        Drive driveService = new Drive.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("spring-test-app")
                .build();

        return driveService;
    }
}
