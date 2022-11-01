package com.example.garageshopfinalassignment.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="files")
public class File {
    @Id
    @GeneratedValue
    private  Long id;
    private String fileName;
    private String fileType;
    @Lob
    private byte [] docFile;

// relationships
    @OneToOne(mappedBy = "carDocuments")
    private Car car;

// constructors
    public File() {
    }

    public File(String fileName, String fileType, byte[] docFile) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.docFile = docFile;
    }

    // getters
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

// setters
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
