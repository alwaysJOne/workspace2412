package com.kh.boot.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.kh.boot.domain.vo.Form;
import com.kh.boot.domain.vo.Member;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Driver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleAPiService {

    @Value("${google.login-api.client-id}")
    private String googleLoginApiClientId;

    @Value("${google.login-api.redirect-url}")
    private String googleLoginApiRedirectUrl;

    @Value("${google.login-api.client-secret}")
    private String gooleLoginApiClientSecret;

    public Map<String, String> requestGoogleUserInfo(String code) {
        String tokenResponse = requestGetGoogleToken(code);


        String accessToekn = extractAccessToken(tokenResponse);

        String userInfoResponse = getUserInfo(accessToekn);

        String email = extractUserEmail(userInfoResponse);

        Map<String, String> result = new HashMap<String, String>();
        result.put("email", email);
        result.put("access_token", accessToekn);
        return result;
    }

    private String getUserInfo(String accessToken) {
        //구글 사용자 정보 api
        String url = "https://www.googleapis.com/oauth2/v3/userinfo";

        //HTTP헤더설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        //Get요청이므로 헤더만 포함한다.
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        //Get요청으로 사용자정보 가져오기
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        //응답 결과 반환
        return responseEntity.getBody();
    }

    private String extractAccessToken(String tokenResponse) {
        //JSON파싱해서 access_token추출하기

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(tokenResponse);
            return jsonNode.get("access_token").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String extractUserEmail(String userInfoResponse) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode jsonNode = mapper.readTree(userInfoResponse);
            return jsonNode.get("email").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String requestGetGoogleToken(String code) {
        String url = "https://oauth2.googleapis.com/token";

        //파라미터 설정
        String params = "code=" + code + "&grant_type=authorization_code"
                + "&client_id=" + googleLoginApiClientId
                + "&client_secret=" + gooleLoginApiClientSecret
                + "&redirect_uri=" + googleLoginApiRedirectUrl;


        //HTTP헤더설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); //form타입

        //HTTP POST요청 전달
        HttpEntity<String> entity = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();

        //구글 토큰 POST요청
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        //응답 결과 반환
        return responseEntity.getBody();
    }

    public List<File> getGoogleForms(String accessToken) {
        Drive driveService = getDriveService(accessToken);

        FileList result = null;
        try {
            result = driveService.files().list()
                    .setQ("mimeType='application/vnd.google-apps.form'")             //검색필터
                    .setSpaces("drive")
                    .setFields("files(id, name, createdTime)")
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result.getFiles();
    }

    //accesstoken을 사용해서 google drive service초기화
    private Drive getDriveService(String accessToken) {
        Drive driveService = null;
        try {
            //googleApi 요청을 보낼 때 사용하는 안전한 HTTP클라이언트 설정
            final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            //응답 json을 직렬화/역직렬화하기위한 파셔
            final JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();

            //구글 인증정보를 담는 객체 -> access_token을 통해서 직접 인증
            GoogleCredential credential = new GoogleCredential.Builder()
                    .setTransport(httpTransport)
                    .setJsonFactory(jacksonFactory)
                    .setClientSecrets(googleLoginApiClientId, gooleLoginApiClientSecret)
                    .build()
                    .setAccessToken(accessToken);

            //driveService를 가져오면
            driveService = new Drive.Builder(httpTransport, jacksonFactory, credential)
                    .setApplicationName("spring-test-app")
                    .build();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return driveService;
    }
}
