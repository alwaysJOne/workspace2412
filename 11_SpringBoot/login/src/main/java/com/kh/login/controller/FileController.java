package com.kh.login.controller;

import com.kh.login.service.FileService;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/files")
@RestController
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload-url")
    public ResponseEntity<String> getUploadUrl(@RequestParam String fileName,
                                               @RequestParam String contentType,
                                               @RequestParam(required = false, defaultValue = "") String path) {

        //확장자 추출
        String extension = "";
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            extension = fileName.substring(lastDotIndex);
        }

        //경로 + 변경된이름 + 확장자 = 저장할 이름
        String changeName = path + UUID.randomUUID() + extension;
        String presignedUrl = fileService.generatePresignedUploadUrl(changeName, contentType);

        return ResponseEntity.ok(presignedUrl);
    }
}
