package com.example.garageshopfinalassignment.models;

import javax.persistence.*;

@Entity
@Table(name="files")
public class File {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private  Long id;
    private String fileName;
    private String fileType;
    @Lob
    private byte [] docFile;

    @OneToOne(mappedBy = "carDocuments")
    private Car car;

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getDocFile() {
        return docFile;
    }

    public Car getCar() {
        return car;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setDocFile(byte[] docFile) {
        this.docFile = docFile;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
