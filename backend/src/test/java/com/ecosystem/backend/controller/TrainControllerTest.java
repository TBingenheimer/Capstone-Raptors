package com.ecosystem.backend.controller;

import com.ecosystem.backend.models.Train;
import com.ecosystem.backend.service.TrainService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainController.class)
@AutoConfigureMockMvc(addFilters = false)
class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainService trainService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTrain_shouldReturnList_whenTrainExists() throws Exception {
        Train train = new Train("1", "tournament1", "user1");
        Mockito.when(trainService.getTrain("tournament1"))
                .thenReturn(Optional.of(List.of(train)));

        mockMvc.perform(get("/api/train/getTrain/tournament1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].tournamentId").value("tournament1"))
                .andExpect(jsonPath("$[0].userId").value("user1"));
    }

    @Test
    void getTrain_shouldReturn404_whenTrainNotFound() throws Exception {
        Mockito.when(trainService.getTrain("tournamentX"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/train/getTrain/tournamentX"))
                .andExpect(status().isNotFound());
    }

    @Test
    void rideTrain_shouldCallService() throws Exception {
        Train train = new Train("1", "tournament1", "user1");

        mockMvc.perform(post("/api/train/rideTrain")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andExpect(status().isOk());

        Mockito.verify(trainService).rideTrain(any(Train.class));
    }

    @Test
    void leaveTrain_shouldCallService() throws Exception {
        Train train = new Train("1", "tournament1", "user1");

        mockMvc.perform(delete("/api/train/leaveTrain")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(train)))
                .andExpect(status().isOk());

        Mockito.verify(trainService).deleteTrain(any(Train.class));
    }
}
