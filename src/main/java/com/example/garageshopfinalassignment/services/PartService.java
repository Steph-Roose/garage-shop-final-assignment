package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.PartDto;
import com.example.garageshopfinalassignment.models.Part;
import com.example.garageshopfinalassignment.repositories.PartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartService {
    private final PartRepository partRepos;

    public PartService(PartRepository partRepos) {
        this.partRepos = partRepos;
    }

    // methods

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
