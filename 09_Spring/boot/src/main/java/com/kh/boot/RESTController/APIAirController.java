package com.kh.boot.RESTController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequestMapping("/api/air")
public class APIAirController {

    @Value("${open.api.key.air}")
    private String airApiKey;

    @GetMapping(produces = "application/json; charset=UTF-8")
    public String getAir(String location, int count) throws IOException {
        //공공데이터를 요청
        String url = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
        //요청시 전달값에 한글이 있다면 인코딩 작업 후 전송하기
        url += "?sidoName=" + URLEncoder.encode(location, "UTF-8");
        url += "&numOfRows=" + count;
        url += "&returnType=json";
        url += "&serviceKey=" + airApiKey;

        log.info("url {}", url);

        //1. 요청하고자하는 url을 전달하면서 java.net.URL객체 생성
        URL requestURL = new URL(url);

        //2. 만들어진 URL객체를 가지고 HttpURLConnection 객체 생성
        HttpURLConnection urlConnection = (HttpURLConnection)requestURL.openConnection();

        //3. 요청에 필요한 Header정보 설정하기
        urlConnection.setRequestMethod("GET");

        //서버에 요청을 보내고 응답값을 가져올 수 있는 inputStream을 받아 보조스트림을 연결해준다.
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

        String result = "";
        String line;
        while ((line = br.readLine()) != null) {
            result += line;
        }

        br.close();
        urlConnection.disconnect();

        log.info("result {}", result);
        return result;
    }
}
