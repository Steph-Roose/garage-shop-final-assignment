package com.example.garageshopfinalassignment.services;

import com.example.garageshopfinalassignment.dtos.ActionDto;
import com.example.garageshopfinalassignment.exceptions.RecordNotFoundException;
import com.example.garageshopfinalassignment.models.Action;
import com.example.garageshopfinalassignment.repositories.ActionRepository;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@AutoConfigureMockMvc(addFilters = false)
class ActionServiceTest {
    @MockBean
    JwtService jwtService;

    @MockBean
    JwtRequestFilter jwtRequestFilter;

    @Mock
    ActionRepository actionRepos;

    @InjectMocks
    ActionService actionService;

    @Captor
    ArgumentCaptor<Action> captor;

    Action action1;
    Action action2;

    @BeforeEach
    void setUp() {
        action1 = new Action(1L, "Check-up", 45.00);
        action2 = new Action(2L, "Change tires", 50.00);
    }

    @Test
    void addAction() {
        when(actionRepos.save(action1)).thenReturn(action1);

        actionService.addAction(actionService.toActionDto(action1));
        verify(actionRepos, times(1)).save(captor.capture());
        Action action = captor.getValue();

        assertEquals(action1.getId(), action.getId());
        assertEquals(action1.getActionName(), action.getActionName());
        assertEquals(action1.getActionCost(), action.getActionCost());
    }

    @Test
    void getAllActions() {
        when(actionRepos.findAll()).thenReturn(List.of(action1, action2));

        List<Action> actions = actionRepos.findAll();
        List<ActionDto> dtos = actionService.getAllActions();

        assertEquals(actions.get(0).getId(), dtos.get(0).getId());
        assertEquals(actions.get(0).getActionName(), dtos.get(0).getActionName());
        assertEquals(actions.get(0).getActionCost(), dtos.get(0).getActionCost());
        assertEquals(actions.get(1).getId(), dtos.get(1).getId());
        assertEquals(actions.get(1).getActionName(), dtos.get(1).getActionName());
        assertEquals(actions.get(1).getActionCost(), dtos.get(1).getActionCost());
    }

    @Test
    void getActionById() {
        when(actionRepos.findById(1L)).thenReturn(Optional.of(action1));

        Action action = actionRepos.findById(1L).get();
        ActionDto dto = actionService.getActionById(1L);

        assertEquals(action.getId(), dto.getId());
        assertEquals(action.getActionName(), dto.getActionName());
        assertEquals(action.getActionCost(), dto.getActionCost());
    }

    @Test
    void getActionByIdThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> actionService.getActionById(null));
    }

    @Test
    void updateAction() {
        when(actionRepos.findById(2L)).thenReturn(Optional.of(action2));

        ActionDto dto = new ActionDto(2L, "Change tires", 75.00);

        when(actionRepos.save(actionService.toAction(dto))).thenReturn(action2);

        actionService.updateAction(2L, dto);

        verify(actionRepos, times(1)).save(captor.capture());

        Action action = captor.getValue();

        assertEquals(dto.getId(), action.getId());
        assertEquals(dto.getActionName(), action.getActionName());
        assertEquals(dto.getActionCost(), action.getActionCost());
    }

    @Test
    void updateActionThrowsExceptionTest() {
        ActionDto dto = new ActionDto(2L, "Change tires", 75.00);

        assertThrows(RecordNotFoundException.class, () -> actionService.updateAction(null, dto));
    }

    @Test
    void deleteAction() {
        when(actionRepos.findById(1L)).thenReturn(Optional.of(action1));

        actionService.deleteAction(1L);

        verify(actionRepos).deleteById(1L);
    }

    @Test
    void deleteActionThrowsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> actionService.deleteAction(null));
    }
}