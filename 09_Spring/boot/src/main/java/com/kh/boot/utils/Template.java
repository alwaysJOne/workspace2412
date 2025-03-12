package com.kh.boot.utils;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

public class Template {
    public static String saveFile(MultipartFile file, HttpSession session, String path) {
        //파일원본명
        String originName = file.getOriginalFilename();

        //확장자
        String ext = originName.substring(originName.lastIndexOf("."));

        //년월일시분초
        String currentTime = String.valueOf(System.currentTimeMillis());

        //5자리 랜덤값
        int random = (int) (Math.random() * 10000);

        String changeName = "kh_" + currentTime + random + ext;
    }
}
