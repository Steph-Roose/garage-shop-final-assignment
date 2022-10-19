package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.repositories.PartRepository;
import org.springframework.stereotype.Service;

@Service
public class PartService {
    private final PartRepository partRepos;

    public PartService(PartRepository partRepos) {
        this.partRepos = partRepos;
    }
}
