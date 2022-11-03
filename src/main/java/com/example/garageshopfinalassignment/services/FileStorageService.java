package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.File;
import com.example.garageshopfinalassignment.repositories.FileRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class FileStorageService {
    private final FileRepository docFileRepos;

    public FileStorageService(FileRepository docFileRepos) {
        this.docFileRepos = docFileRepos;
    }

    public File storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        File docFile = new File(fileName, file.getContentType(), file.getBytes());

        return docFileRepos.save(docFile);
    }

    public File getFileById(Long id) {
        if(docFileRepos.findById(id).isPresent()) {
            return docFileRepos.findById(id).get();
        } else {
            throw new RecordNotFoundException("Couldn't find file");
        }

    }
}
