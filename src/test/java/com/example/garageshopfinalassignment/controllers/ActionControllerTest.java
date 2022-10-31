package com.example.garageshopfinalassignment.controllers;

import com.example.garageshopfinalassignment.dtos.ActionDto;
import com.example.garageshopfinalassignment.models.Action;
import com.example.garageshopfinalassignment.services.ActionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ActionController.class)
class ActionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActionService actionService;

    Action action1;
    Action action2;
    ActionDto actionDto1;
    ActionDto actionDto2;

    @BeforeEach
    void setUp() {
        action1 = new Action(1L, "check-up", 45.00);
        action2 = new Action(2L, "change tires", 200.00);

        actionDto1 = new ActionDto(1L, "check-up", 45.00);
        actionDto2 = new ActionDto(2L, "change tires", 200.00);
    }

    @Test
    void addAction() throws Exception {
        given(actionService.addAction(actionDto1)).willReturn(actionDto1);

        mockMvc.perform(post("/actions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(actionDto1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(1L))
                .andExpect(jsonPath("actionName").value("check-up"))
                .andExpect(jsonPath("actionCost").value(45.00));
    }

    @Test
    @Disabled
    void getAllActions() {
    }

    @Test
    @Disabled
    void getAction() {
    }

    @Test
    @Disabled
    void updateAction() {
    }

    @Test
    @Disabled
    void deleteAction() {
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