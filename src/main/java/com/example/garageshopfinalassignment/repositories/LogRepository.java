package com.example.garageshopfinalassignment.repositories;

import com.example.garageshopfinalassignment.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findAllLogsByCarId(Long carId);
}
