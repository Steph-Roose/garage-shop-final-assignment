package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.LogDto;
import com.example.garageshopfinalassignment.dtos.LogInputDto;
import com.example.garageshopfinalassignment.dtos.PartDto;
import com.example.garageshopfinalassignment.dtos.UsedPartsDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Log;
import com.example.garageshopfinalassignment.models.Part;
import com.example.garageshopfinalassignment.repositories.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepos;
    private final CarRepository carRepos;
    private final ActionRepository actionRepos;
    private final PartRepository partRepos;
    private final PartService partService;
    private final CarService carService;
    private final ActionService actionService;

    public LogService(LogRepository logRepos, CarRepository carRepos, ActionRepository actionRepos, PartRepository partRepos, PartService partService, CarService carService, ActionService actionService) {
        this.logRepos = logRepos;
        this.carRepos = carRepos;
        this.actionRepos = actionRepos;
        this.partRepos = partRepos;
        this.partService = partService;
        this.carService = carService;
        this.actionService = actionService;
    }

// methods
    public LogDto addLog(LogInputDto dto) {
        Log log = new Log();

        var optionalCar = carRepos.findById(dto.getCarId());
        var optionalAction = actionRepos.findById(dto.getActionId());

        if(optionalCar.isPresent() && optionalAction.isPresent()) {
            var car = optionalCar.get();
            var action = optionalAction.get();

            log.setTotalPartsCost(0.0);
            log.setTotalCost(action.getActionCost());
            log.setCar(car);
            log.setAction(action);

            if(action.getActionName().equals("Check-up"))  {
                log.setLogStatus(Log.LogStatus.SCHEDULED);
            } else {
                log.setLogStatus(Log.LogStatus.NEEDS_APPROVAL);
            }

        } else if(optionalCar.isEmpty()) {
            throw new RecordNotFoundException("Couldn't find car");
        } else {
            throw new RecordNotFoundException("Couldn't find action");
        }

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

    public List<LogDto> getLogsByStatus(Long carId, String status) {
        List<LogDto> logDtoList = getLogsByCarId(carId);
        List<LogDto> newList = new ArrayList<>();

        for(LogDto dto : logDtoList) {
            if(dto.getLogStatus() == Log.LogStatus.valueOf(status)) {
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
        var optionalLog = logRepos.findById(id);

        if(optionalLog.isPresent()) {
            Log log = optionalLog.get();
            Log log1 = toLog(dto);

            log1.setId(log.getId());

            logRepos.save(log1);

            return toLogDto(log1);
        } else {
            throw new RecordNotFoundException("Couldn't find log");
        }
    }

    public LogDto updateStatus(Long id, String status) {
        var optionalLog = logRepos.findById(id);

        if(optionalLog.isPresent()) {
            Log log = optionalLog.get();

            log.setLogStatus(Log.LogStatus.valueOf(status));

            logRepos.save(log);

            return toLogDto(log);
        } else {
            throw new RecordNotFoundException("Couldn't find log");
        }
    }

    public List<PartDto> addUsedParts(Long id, UsedPartsDto dto) {
        var optionalLog = logRepos.findById(id);
        var optionalPart = partRepos.findById(dto.getPartId());

        if(optionalLog.isPresent() && optionalPart.isPresent()) {
            var log = optionalLog.get();
            var part = optionalPart.get();

            List<Part> usedParts = log.getUsedParts();
            for(int i = 0; i < dto.getQuantity(); i++) {
                usedParts.add(part);
            }
            log.setUsedParts(usedParts);

            calculateCost(log);

            logRepos.save(log);

            return partService.partListToPartDtoList(usedParts);

        } else if(optionalLog.isEmpty()) {
            throw new RecordNotFoundException("Couldn't find log");
        } else {
            throw new RecordNotFoundException("Couldn't find part");
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

    public double calculateCost(Log log) {
        double actionCost = log.getAction().getActionCost();
        double partsCost = 0.0;

        List<Part> usedParts = log.getUsedParts();

        if(!usedParts.isEmpty()) {
            for(Part part : usedParts) {
                partsCost += part.getUnitPrice();
            }

            log.setTotalPartsCost(partsCost);
        }

        log.setTotalCost(log.getTotalPartsCost() + actionCost);
        logRepos.save(log);

        return log.getTotalCost();
    }

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
        log.setTotalPartsCost(dto.getTotalPartsCost());
        log.setTotalCost(dto.getTotalCost());

        var optionalCar = carRepos.findById(dto.getCarId());
        if(optionalCar.isPresent()) {
            var car = optionalCar.get();
            log.setCar(car);
        } else {
            throw new RecordNotFoundException("Couldn't find matching car");
        }

        var optionalAction = actionRepos.findById(dto.getActionDto().getId());
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
        dto.setTotalPartsCost(log.getTotalPartsCost());
        dto.setTotalCost(log.getTotalCost());
        dto.setCarId(log.getCar().getId());
        dto.setActionDto(actionService.toActionDto(log.getAction()));
        if(log.getUsedParts() != null) {
            dto.setUsedPartsDto(partService.partListToPartDtoList(log.getUsedParts()));
        }

        return dto;
    }
}
