package com.example.garageshopfinalassignment.repositories;

import com.example.garageshopfinalassignment.models.Log;
import com.example.garageshopfinalassignment.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findAllLogsByCarId(Long carId);
    List<Log> findByAction_Id(Long actionId);

    List<Log> findByUsedPartsContains(Part part);
}
