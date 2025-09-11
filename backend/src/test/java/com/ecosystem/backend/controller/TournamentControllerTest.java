package com.ecosystem.backend.controller;

import com.ecosystem.backend.models.Tournament;
import com.ecosystem.backend.repository.TournamentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class TournamentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TournamentRepo tournamentRepo;

    @BeforeEach
    void cleanDb() {
        tournamentRepo.deleteAll();
    }


    @Test
    void getAllTournaments_shoud_return_empty_TournamentsObject() throws Exception {
        mockMvc.perform(get("/api/tournament/getAllTournaments"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {"tournaments":[
                      {
                        "id":"0",
                        "name":"",
                        "description":"",
                        street: "",
                        zip: "",
                        city: "",
                        "startDateTime":"-",
                        "endDateTime": "-",
                        "participants":0
                        }
                    ]}
                    """));;
    }

    @Test
    void getTournament_should_return_defaultTournamentObject() throws Exception {
        mockMvc.perform(get("/api/tournament/getTournament/0"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    
                      {
                        "id":"0",
                        "name":"",
                        "description":"",
                        street: "",
                        zip: "",
                        city: "",
                        "startDateTime":"-",
                        "endDateTime": "-",
                        "participants":0
                        }
                    
                    """));;
    }

    @Test
    void saveTournament_should_return_tournamentObject() throws Exception {
        mockMvc.perform(
                post("/api/tournament/createTournament")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"id":"0","name":"","description":"","street":"","zip":"","city":"","startDateTime":"-","endDateTime": "-","participants":0}
                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                      {
                        "id":"0",
                        "name":"",
                        "description":"",
                        street: "",
                        zip: "",
                        city: "",
                        "startDateTime":"-",
                        "endDateTime": "-",
                        "participants":0
                        }
                    
                    """));
    }
}