package com.ecosystem.backend.controller;

import com.ecosystem.backend.repository.CarsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarsRepo carsRepo;

    @BeforeEach
    void cleanDb() {
        carsRepo.deleteAll();
    }

    @Test
    void getCarsForTournament_shouldReturnCars() throws Exception {
        mockMvc.perform(get("/api/cars/getCars/0"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [{
                            "driverId":"0",
                            "tournamentId":"0",
                            "availableSeats":"0",
                            "takeOffTime":"n/a",
                            "shotgun": "",
                            "rear":[""]
                        }]
                    """));;
    }

}