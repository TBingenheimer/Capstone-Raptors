package com.ecosystem.backend.models;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TournamentRankingResultTest {

    @Test
    void testTournamentRankingResultCreation() {
        TeamRanking team1 = new TeamRanking(1, "Team A");
        TeamRanking team2 = new TeamRanking(2, "Team B");

        Map<String, TeamRanking> rankings = Map.of(
                "1", team1,
                "2", team2
        );

        TournamentRankingResult result = new TournamentRankingResult(
                100,
                "Spring Tournament",
                rankings
        );

        assertEquals(100, result.tournament_id());
        assertEquals("Spring Tournament", result.tournament_name());
        assertNotNull(result.rankings());
        assertEquals(2, result.rankings().size());

        // Prüfen der Map-Inhalte
        assertEquals(team1, result.rankings().get("1"));
        assertEquals(team2, result.rankings().get("2"));
    }

    @Test
    void testEmptyRankings() {
        TournamentRankingResult result = new TournamentRankingResult(
                101,
                "Empty Tournament",
                Map.of()
        );

        assertEquals(101, result.tournament_id());
        assertEquals("Empty Tournament", result.tournament_name());
        assertTrue(result.rankings().isEmpty());
    }
}
