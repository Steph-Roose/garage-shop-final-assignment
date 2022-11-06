package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.FileUploadResponse.FileUploadResponse;
import com.example.garageshopfinalassignment.models.FileDocument;
import com.example.garageshopfinalassignment.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/uploadfile")
    public @ResponseBody FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("car_id") Long carId) throws IOException {
        FileDocument fileDocument = fileService.uploadFileDocument(file, carId);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadfile/").path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileDocument.getFileName(), contentType, url);
    }

    @GetMapping("/downloadfile/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("filename") String fileName, HttpServletRequest request) {
        return fileService.downloadFileDocument(fileName, request);
    }
}
