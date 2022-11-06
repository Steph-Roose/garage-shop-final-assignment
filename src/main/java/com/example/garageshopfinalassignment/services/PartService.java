package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.PartDto;
import com.example.garageshopfinalassignment.exceptions.BadRequestException;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Part;
import com.example.garageshopfinalassignment.repositories.LogRepository;
import com.example.garageshopfinalassignment.repositories.PartRepository;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PartService {
    private final PartRepository partRepos;
    private final LogRepository logRepos;

    public PartService(PartRepository partRepos, LogRepository logRepos) {
        this.partRepos = partRepos;
        this.logRepos = logRepos;
    }

    public PartDto addPart(PartDto dto) {
        Part part = toPart(dto);
        partRepos.save(part);

        return toPartDto(part);
    }

    public List<PartDto> getAllParts() {
        return partListToPartDtoList(partRepos.findAll());
    }

    public PartDto getPartById(Long id) {
        if(partRepos.findById(id).isPresent()) {
            return toPartDto(partRepos.findById(id).get());
        } else {
            throw new RecordNotFoundException("Couldn't find part");
        }
    }

    public PartDto updatePart(Long id, PartDto dto) {
        if(partRepos.findById(id).isPresent()) {
            Part part = partRepos.findById(id).get();
            Part part1 = toPart(dto);
            part1.setId(part.getId());
            partRepos.save(part1);

            return toPartDto(part1);
        } else {
            throw new RecordNotFoundException("Couldn't find part");
        }
    }

    public String deletePart(Long id) {
        var optionalPart = partRepos.findById(id);
        if(optionalPart.isPresent()) {
            Part part = optionalPart.get();
            var optionalLogList = logRepos.findByUsedPartsContains(part);

            if(optionalLogList.isEmpty()) {
                String partName = part.getPartName();
                partRepos.deleteById(id);

                return "Part deleted: " + partName;
            } else {
                throw new BadRequestException("Can't delete part. Part is used in " + optionalLogList.size() + " log(s).");
            }
        } else {
            throw new RecordNotFoundException("Couldn't find part");
        }
    }

    public List<PartDto> partListToPartDtoList(List<Part> parts) {
        List<PartDto> partDtoList = new ArrayList<>();

        for(Part part : parts) {
            PartDto dto = toPartDto(part);

            partDtoList.add(dto);
        }
        return partDtoList;
    }

    public Part toPart(PartDto dto) {
        var part = new Part();

        if(dto.getId() != null) {
            part.setId(dto.getId());
        }
        part.setPartName(dto.getPartName());
        part.setUnitPrice(dto.getUnitPrice());

        return part;
    }

    public PartDto toPartDto(Part part) {
        PartDto dto = new PartDto();

        dto.setId(part.getId());
        dto.setPartName(part.getPartName());
        dto.setUnitPrice(part.getUnitPrice());

        return dto;
    }
}

