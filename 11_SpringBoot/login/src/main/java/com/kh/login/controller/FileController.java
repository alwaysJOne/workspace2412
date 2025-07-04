package com.kh.login.controller;

import com.kh.login.domain.File;
import com.kh.login.dto.file.CompleteUploadRequestDto;
import com.kh.login.dto.file.DownloadUrlResponseDto;
import com.kh.login.dto.file.UploadUrlResponseDto;
import com.kh.login.service.FileService;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<UploadUrlResponseDto> getUploadUrl(@RequestParam String file_name,
                                                             @RequestParam String content_type,
                                                             @RequestParam(required = false, defaultValue = "") String path) {

        //확장자 추출
        String extension = "";
        int lastDotIndex = file_name.lastIndexOf('.');
        if (lastDotIndex > 0) {
            extension = file_name.substring(lastDotIndex);
        }

        //경로 + 변경된이름 + 확장자 = 저장할 이름
        String changeName = path + UUID.randomUUID() + extension;
        String presignedUrl = fileService.generatePresignedUploadUrl(changeName, content_type);

        return ResponseEntity.ok(new UploadUrlResponseDto(changeName, presignedUrl));
    }

    @GetMapping("/{fileId}/download-url")
    public ResponseEntity<?> getDownloadUrl(@PathVariable Long fileId) {
        File file = fileService.getFile(fileId);
        String presigendUrl = fileService.generatePresignedDownloadUrl(file.getChangeName());

        return ResponseEntity.ok(new DownloadUrlResponseDto(presigendUrl, file.getOriginalName()));
    }

    @PostMapping("/complete")
    public ResponseEntity<File> completeUpload(@RequestBody CompleteUploadRequestDto request) {
        File file = fileService.saveFileInfo(request.getOriginal_name(), request.getChange_name(),
                request.getContent_type());
        return ResponseEntity.ok(file);
    }

    @GetMapping
    public ResponseEntity<List<File>> getFiles() {
        return ResponseEntity.ok(fileService.getAllFiles());
    }
}
