package com.ecosystem.backend.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TugenyResultTest {

    @Test
    void testTugenyResultCreation() {
        Match match1 = new Match(
                "1", "10", "20", "10", "Match 1",
                "Tournament 1", "City A", "Team A", "Team B", "Team A",
                "Mode1", "2", "1", "21", "15", "21:15", "Group A", "G1"
        );

        Match match2 = new Match(
                "1", "30", "40", "40", "Match 2",
                "Tournament 1", "City A", "Team C", "Team D", "Team D",
                "Mode1", "0", "2", "10", "21", "10:21", "Group A", "G1"
        );

        TugenyResult result = new TugenyResult(List.of(match1, match2));

        assertNotNull(result.matches());
        assertEquals(2, result.matches().size());
        assertEquals(match1, result.matches().get(0));
        assertEquals(match2, result.matches().get(1));
    }

    @Test
    void testEmptyMatchesList() {
        TugenyResult result = new TugenyResult(List.of());

        assertNotNull(result.matches());
        assertTrue(result.matches().isEmpty());
    }
}
