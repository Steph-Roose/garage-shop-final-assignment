package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Car;
import com.example.garageshopfinalassignment.models.FileDocument;
import com.example.garageshopfinalassignment.repositories.CarRepository;
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
public class FileService {
    private final FileRepository fileRepos;
    private final CarRepository carRepos;

    public FileService(FileRepository fileRepos, CarRepository carRepos) {
        this.fileRepos = fileRepos;
        this.carRepos = carRepos;
    }

    public FileDocument uploadFileDocument(MultipartFile file, Long carId) throws IOException {
        var optionalCar = carRepos.findById(carId);

        if(optionalCar.isPresent()) {
            Car car = optionalCar.get();

            String name = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

            FileDocument fileDocument = new FileDocument();
            fileDocument.setFileName(name);
            fileDocument.setDocFile(file.getBytes());

            FileDocument fileDocument1 = fileRepos.save(fileDocument);

            car.setCarDocuments(fileDocument1);
            carRepos.save(car);

            return fileDocument;
        } else {
            throw new RecordNotFoundException("Couldn't find car");
        }
    }

    public ResponseEntity<byte[]> downloadFileDocument(String fileName, HttpServletRequest request){

        FileDocument document = fileRepos.findByFileNameContainingIgnoreCase(fileName);

        String mimeType = request.getServletContext().getMimeType(document.getFileName());

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + document.getFileName()).body(document.getDocFile());
    }
}
