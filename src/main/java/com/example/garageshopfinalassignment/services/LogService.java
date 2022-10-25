package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.LogDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
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
    public LogDto addLog(LogDto dto) {
        Log log = toLog(dto);
        logRepos.save(log);

        return toLogDto(log);
    }

    public List<LogDto> getAllLogsByCarId(Long carId) {
        List<Log> logList = logRepos.findAllLogsByCarId(carId);

        if(logList != null) {
            return logListToLogDtoList(logList);
        } else {
            throw new RecordNotFoundException("No logs found");
        }
    }

    public LogDto getLogById(Long id) {
        if(logRepos.findById(id).isPresent()) {
            Log log = logRepos.findById(id).get();

            return toLogDto(log);
        } else {
            throw new RecordNotFoundException("Couldn't find log");
        }
    }

    public LogDto updateLog(Long id, LogDto dto) {
        if(logRepos.findById(id).isPresent()) {
            Log log = logRepos.findById(id).get();
            Log log1 = toLog(dto);
            log1.setId(log.getId());

            logRepos.save(log1);

            return toLogDto(log1);
        } else {
            throw new RecordNotFoundException("Couldn't find log");
        }
    }

    public String deleteLog(Long id) {
        if(logRepos.findById(id).isPresent()) {
            logRepos.deleteById(id);

            return "Log deleted";
        } else {
            throw new RecordNotFoundException("Couldn't find log");
        }
    }

    // add parts to a log

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
