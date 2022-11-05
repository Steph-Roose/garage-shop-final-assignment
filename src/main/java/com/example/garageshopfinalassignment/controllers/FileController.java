package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.FileUploadResponse.FileUploadResponse;
import com.example.garageshopfinalassignment.models.File;
import com.example.garageshopfinalassignment.services.FileStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
public class FileController {
    private final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("upload")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        File fileDocument = fileStorageService.uploadFile(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileDocument.getFileName(), url, contentType );
    }

    @GetMapping("download/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable("id") Long id, HttpServletRequest request) {
        return fileStorageService.downloadFile(id, request);
    }
}
