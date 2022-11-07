package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.PartDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Part;
import com.example.garageshopfinalassignment.repositories.LogRepository;
import com.example.garageshopfinalassignment.repositories.PartRepository;
import com.example.garageshopfinalassignment.security.JwtRequestFilter;
import com.example.garageshopfinalassignment.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@AutoConfigureMockMvc(addFilters = false)
class PartServiceTest {
    @MockBean
    JwtService jwtService;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @Mock
    PartRepository partRepos;

    @Mock
    LogRepository logRepos;

    @InjectMocks
    PartService partService;

    @Captor
    ArgumentCaptor<Part> captor;

    Part part1;
    Part part2;
    PartDto dto1;
    PartDto dto2;

    @BeforeEach
    void setUp() {
        part1 = new Part(1L, "Summer tire", 50.00);
        part2 = new Part(2L, "Battery", 200.00);
        dto1 = new PartDto(1L, "Summer tire", 50.00);
        dto2 = new PartDto(2L, "Battery", 100.00);
    }

    @Test
    void addPart() {
        when(partRepos.save(part1)).thenReturn(part1);

        partService.addPart(partService.toPartDto(part1));
        verify(partRepos, times(1)).save(captor.capture());
        Part part = captor.getValue();

        assertEquals(part1.getId(), part.getId());
        assertEquals(part1.getPartName(), part.getPartName());
        assertEquals(part1.getUnitPrice(), part.getUnitPrice());
    }

    @Test
    void getAllParts() {
        when(partRepos.findAll()).thenReturn(List.of(part1, part2));

        List<Part> parts = partRepos.findAll();
        List<PartDto> dtos = partService.getAllParts();

        assertEquals(parts.get(0).getId(), dtos.get(0).getId());
        assertEquals(parts.get(0).getPartName(), dtos.get(0).getPartName());
        assertEquals(parts.get(0).getUnitPrice(), dtos.get(0).getUnitPrice());
        assertEquals(parts.get(1).getId(), dtos.get(1).getId());
        assertEquals(parts.get(1).getPartName(), dtos.get(1).getPartName());
        assertEquals(parts.get(1).getUnitPrice(), dtos.get(1).getUnitPrice());
    }

    @Test
    void getPartById() {
        when(partRepos.findById(1L)).thenReturn(Optional.of(part1));

        Part part = partRepos.findById(1L).get();
        PartDto dto = partService.getPartById(1L);

        assertEquals(part.getId(), dto.getId());
        assertEquals(part.getPartName(), dto.getPartName());
        assertEquals(part.getUnitPrice(), dto.getUnitPrice());
    }

    @Test
    void getPartByIdThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> partService.getPartById(null));
    }

    @Test
    void updatePart() {
        when(partRepos.findById(2L)).thenReturn(Optional.of(part2));
        when(partRepos.save(partService.toPart(dto2))).thenReturn(part2);

        partService.updatePart(2L, dto2);

        verify(partRepos, times(1)).save(captor.capture());

        Part part = captor.getValue();

        assertEquals(dto2.getId(), part.getId());
        assertEquals(dto2.getPartName(), part.getPartName());
        assertEquals(dto2.getUnitPrice(), part.getUnitPrice());
    }

    @Test
    void updatePartThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> partService.updatePart(null, dto1));
    }

    @Test
    void deletePart() {
        List emptyList = new ArrayList();

        when(partRepos.findById(1L)).thenReturn(Optional.of(part1));
        when(logRepos.findByUsedPartsContains(part1)).thenReturn(emptyList);

        partService.deletePart(1L);

        verify(partRepos).deleteById(1L);
    }

    @Test
    void deletePartThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> partService.deletePart(null));
    }
}