package com.example.garageshopfinalassignment.repositories;

import com.example.garageshopfinalassignment.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface FileRepository extends JpaRepository<File, Long> {
}
