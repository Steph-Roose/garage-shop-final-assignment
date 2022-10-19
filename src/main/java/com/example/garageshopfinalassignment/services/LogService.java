package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.repositories.LogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private final LogRepository logRepos;

    public LogService(LogRepository logRepos) {
        this.logRepos = logRepos;
    }
}
