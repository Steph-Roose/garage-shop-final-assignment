package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.repositories.ActionRepository;
import org.springframework.stereotype.Service;

@Service
public class ActionService {
    private final ActionRepository actionRepos;

    public ActionService(ActionRepository actionRepos) {
        this.actionRepos = actionRepos;
    }
}
