package com.ecosystem.backend.service;

import com.ecosystem.backend.models.*;
import com.ecosystem.backend.repository.TournamentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TournamentServiceTest {

    private TournamentRepo tournamentRepo;
    private TournamentService tournamentService;
    private RestClient restClient;

    @BeforeEach
    void setUp() {
        tournamentRepo = mock(TournamentRepo.class);
        restClient = mock(RestClient.class);
        tournamentService = new TournamentService(tournamentRepo) {
            // Override RestClient to use the mock
            @Override
            public TugenyResult getTugenyResult() {
                return new TugenyResult(List.of(
                        new Match("1","10","20","10","Match 1","Tournament 1","City A","Team A","Team B","Team A",
                                "Mode1","2","1","21","15","21:15","Group A","G1")
                ));
            }

            @Override
            public TournamentRankingResult getTournamentRankings(String tugneyId) {
                return new TournamentRankingResult(
                        1,
                        "Tournament 1",
                        Map.of("1", new TeamRanking(10,"Team A"))
                );
            }
        };
    }

    @Test
    void getTugenyResult_shouldReturnMatches() {
        TugenyResult result = tournamentService.getTugenyResult();

        assertThat(result.matches()).hasSize(1);
        assertThat(result.matches().get(0).name()).isEqualTo("Match 1");
    }

    @Test
    void getTournamentRankings_shouldReturnRankings() {
        TournamentRankingResult result = tournamentService.getTournamentRankings("123");

        assertThat(result.tournament_id()).isEqualTo(1);
        assertThat(result.rankings()).containsKey("1");
        assertThat(result.rankings().get("1").team_name()).isEqualTo("Team A");
    }
}
