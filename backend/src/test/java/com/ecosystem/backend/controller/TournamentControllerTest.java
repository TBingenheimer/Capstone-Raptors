package com.ecosystem.backend.controller;

import com.ecosystem.backend.repository.TournamentRepo;
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
                        "startDate":"-",
                        "endDate": "-",
                        "participants":0
                        }
                    ]}
                    """));;
    }

    @Test
    void getTournament_should_return_defaultTournamentObject() throws Exception {
        mockMvc.perform(get("/api/tournament//getTournament/0"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    
                      {
                        "id":"0",
                        "name":"",
                        "description":"",
                        "startDate":"-",
                        "endDate": "-",
                        "participants":0
                        }
                    
                    """));;
    }

    /*@Test
    void getAllTournaments_Test() {
        Tournament tournament1 = new Tournament("1","9. Turnier zu Münster","Tolles Turnier in Münster","2025-07-27","2025-07-29",7, Arrays.asList("2","3","4","5","6"));
        Tournament tournament2 = new Tournament("2","4. Bochumer Sommerturnier","Tolles Turnier in Bpochum","2025-08-29","2025-08-31",7, Arrays.asList("2","3","4","5","6"));
        Map<String, Tournament> tournaments = new TreeMap();
        tournaments.put("1",tournament1);
        tournaments.put("2",tournament2);

        assertEquals(getAllTournaments(), tournaments);
    }
    @Test
    void getTournamentById_Test() {
        Tournament tournament = new Tournament("1","9. Turnier zu Münster","Tolles Turnier in Münster","2025-07-27","2025-07-29",7, Arrays.asList("2","3","4","5","6"));
        assertEquals(getTournament(1), tournament);
    }*/
}