package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.ActionDto;
import com.example.garageshopfinalassignment.models.Action;
import com.example.garageshopfinalassignment.security.JwtRequestFilter;
import com.example.garageshopfinalassignment.security.JwtService;
import com.example.garageshopfinalassignment.services.ActionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ActionController.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@AutoConfigureMockMvc(addFilters = false)
class ActionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JwtService jwtService;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @MockBean
    private ActionService actionService;

    Action action1;
    Action action2;
    ActionDto dto1;
    ActionDto dto2;
    ActionDto dto3;

    @BeforeEach
    void setUp() {
        action1 = new Action(1L, "Check-up", 45.00);
        action2 = new Action(2L, "Change tires", 50.00);
        dto1 = new ActionDto(1L, "Check-up", 45.00);
        dto2 = new ActionDto(2L, "Change tires", 50.00);
        dto3 = new ActionDto(3L, "Change tires", 50.00);
    }

    @Test
    void addAction() throws Exception {
        given(actionService.addAction(dto1)).willReturn(dto1);

        mockMvc.perform(post("/actions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto2)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("actionName").value("Check-up"))
                .andExpect(jsonPath("actionCost").value(45.00));
    }

    @Test
    void getAllActions() throws Exception {
        given(actionService.getAllActions()).willReturn(List.of(dto1, dto2));

        mockMvc.perform(get("/actions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].actionName").value("Check-up"))
                .andExpect(jsonPath("$[0].actionCost").value(45.00))
                .andExpect(jsonPath("$[0].id").value(2L))
                .andExpect(jsonPath("$[0].actionName").value("Change tires"))
                .andExpect(jsonPath("$[0].actionCost").value(50.00));
    }

    @Test
    void getAction() throws Exception {
        given(actionService.getActionById(1L)).willReturn(dto1);

        mockMvc.perform(get("actions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("actionName").value("Check-up"))
                .andExpect(jsonPath("actionCost").value(45.00));
    }

    @Test
    void updateAction() throws Exception {
        given(actionService.updateAction(2L, dto3)).willReturn(dto2);

        mockMvc.perform(put("/actions/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(2L))
                .andExpect(jsonPath("actionName").value("Change tires"))
                .andExpect(jsonPath("actionCost").value(50.00));
    }

    @Test
    void deleteAction() throws Exception {
        mockMvc.perform(delete("/actions/2"))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final ActionDto obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}