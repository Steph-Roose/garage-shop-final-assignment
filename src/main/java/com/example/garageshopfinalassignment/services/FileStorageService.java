package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.File;
import com.example.garageshopfinalassignment.repositories.FileRepository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Service
public class FileStorageService {
    private final FileRepository docFileRepos;

    public FileStorageService(FileRepository docFileRepos) {
        this.docFileRepos = docFileRepos;
    }

    public File uploadFile(MultipartFile file) throws IOException {
        String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        File file1 = new File();
        file1.setFileName(name);
        file1.setDocFile(file.getBytes());

        docFileRepos.save(file1);

        return file1;
    }

    public ResponseEntity<byte[]> downloadFile(Long id, HttpServletRequest request) {
        if(docFileRepos.findById(id).isPresent()) {
            File file = docFileRepos.findById(id).get();

            String mimeType = request.getServletContext().getMimeType(file.getFileName());

            return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + file.getFileName()).body(file.getDocFile());
        } else {
            throw new RecordNotFoundException("Couldn't find file");
        }

    }
}
