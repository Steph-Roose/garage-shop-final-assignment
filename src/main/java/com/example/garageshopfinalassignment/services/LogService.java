package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.LogDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Log;
import com.example.garageshopfinalassignment.repositories.ActionRepository;
import com.example.garageshopfinalassignment.repositories.CarRepository;
import com.example.garageshopfinalassignment.repositories.CustomerRepository;
import com.example.garageshopfinalassignment.repositories.LogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepos;
    private final CarRepository carRepos;
    private final ActionRepository actionRepos;

    public LogService(LogRepository logRepos, CarRepository carRepos, ActionRepository actionRepos) {
        this.logRepos = logRepos;
        this.carRepos = carRepos;
        this.actionRepos = actionRepos;
    }

// methods
    public LogDto addLog(LogDto dto) {
        Log log = toLog(dto);
        logRepos.save(log);

        return toLogDto(log);
    }

    public List<LogDto> getLogsByCarId(Long carId) {
        List<Log> logList = logRepos.findAllLogsByCarId(carId);

        if(logList != null) {
            return logListToLogDtoList(logList);
        } else {
            throw new RecordNotFoundException("No logs found");
        }
    }

    public List<LogDto> getLogsByStatus(Long carId, Log.LogStatus status) {
        List<LogDto> logDtoList = getLogsByCarId(carId);
        List<LogDto> newList = new ArrayList<>();

        for(LogDto dto : logDtoList) {
            if(dto.getLogStatus() == status) {
                newList.add(dto);
            }
        }

        if(!newList.isEmpty()) {
            return newList;
        } else {
            throw new RecordNotFoundException("No logs found with status: " + status);
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

    public List<Log> logDtoListToLogList(List<LogDto> dtos) {
        List<Log> logList= new ArrayList<>();

        for(LogDto dto : dtos) {
            Log log = toLog(dto);

            logList.add(log);
        }
        return logList;
    }

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

        var optionalCar = carRepos.findById(dto.getCarId());
        if(optionalCar.isPresent()) {
            var car = optionalCar.get();
            log.setCar(car);
        } else {
            throw new RecordNotFoundException("Couldn't find matching car");
        }

        var optionalAction = actionRepos.findById(dto.getActionId());
        if(optionalAction.isPresent()) {
            var action = optionalAction.get();
            log.setAction(action);
        } else {
            throw new RecordNotFoundException("Couldn't find matching action");
        }

        return log;
    }

    public LogDto toLogDto(Log log) {
        LogDto dto = new LogDto();

        dto.setId(log.getId());
        dto.setLogStatus(log.getLogStatus());
        dto.setCreatedOn(log.getCreatedOn());
        dto.setTotalPartsCost(log.getTotalPartsCost());
        dto.setTotalCost(log.getTotalCost());
        dto.setCarId(log.getCar().getId());
        dto.setActionId(log.getAction().getId());

        return dto;
    }
}
