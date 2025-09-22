package com.ecosystem.backend.controller;

import com.ecosystem.backend.models.Tournament;
import com.ecosystem.backend.models.TugenyResult;
import com.ecosystem.backend.models.TournamentRankingResult;
import com.ecosystem.backend.repository.TournamentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void cleanDb() {
        tournamentRepo.deleteAll();
    }

    @Test
    void getAllTournaments_should_return_emptyTournamentsObject() throws Exception {
        mockMvc.perform(get("/api/tournament/getAllTournaments"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {"tournaments":[]}
                    """));
    }

    @Test
    void getTournament_should_return_defaultTournamentObject() throws Exception {
        Tournament tournament = new Tournament("0","","","", "", "", "-", "-", 0,"");
        tournamentRepo.save(tournament);

        mockMvc.perform(get("/api/tournament/getTournament/0"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(tournament)));
    }

    @Test
    void saveTournament_should_return_tournamentObject() throws Exception {
        Tournament tournament = new Tournament("0","","","", "", "", "-", "-", 0,"");

        mockMvc.perform(
                        post("/api/tournament/createTournament")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(tournament)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(tournament)));
    }

    @Test
    void getTugenyResults_should_return_tugenyResult() throws Exception {
        mockMvc.perform(get("/api/tournament/getTugenyResults"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    TugenyResult res = objectMapper.readValue(result.getResponse().getContentAsString(), TugenyResult.class);
                    assert res != null;
                });
    }

    @Test
    void getTournamentRankings_should_return_tournamentRankingResult() throws Exception {
        String tugenyId = "1";
        mockMvc.perform(get("/api/tournament/getTugenyTournamentRanking/" + tugenyId))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    TournamentRankingResult res = objectMapper.readValue(result.getResponse().getContentAsString(), TournamentRankingResult.class);
                    assert res != null;
                });
    }
}
