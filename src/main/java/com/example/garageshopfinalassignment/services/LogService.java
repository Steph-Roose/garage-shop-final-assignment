package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.LogDto;
import com.example.garageshopfinalassignment.models.Log;
import com.example.garageshopfinalassignment.repositories.LogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepos;

    public LogService(LogRepository logRepos) {
        this.logRepos = logRepos;
    }

    // methods

    public List<LogDto> logListToLogDtoList(List<Log> logs) {
        List<LogDto> logDtoList = new ArrayList<>();

        for(Log log : logs) {
            LogDto dto = toLogDto(log);

            logDtoList.add(dto);
        }
        return logDtoList;
    }
    public Log toLog(LogDto dto) {
        var log = new Log();

        log.setLogStatus(dto.getLogStatus());
        log.setCreatedOn(dto.getCreatedOn());
        log.setTotalPartsCost(dto.getTotalPartsCost());
        log.setTotalCost(dto.getTotalCost());

        return log;
    }

    public LogDto toLogDto(Log log) {
        LogDto dto = new LogDto();

        dto.setId(log.getId());
        dto.setLogStatus(log.getLogStatus());
        dto.setCreatedOn(log.getCreatedOn());
        dto.setTotalPartsCost(log.getTotalPartsCost());
        dto.setTotalCost(log.getTotalCost());

        return dto;
    }
}
