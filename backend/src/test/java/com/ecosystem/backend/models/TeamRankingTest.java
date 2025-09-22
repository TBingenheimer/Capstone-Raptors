package com.ecosystem.backend.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamRankingTest {

    @Test
    void testTeamRankingCreation() {
        TeamRanking ranking = new TeamRanking(5, "Cologne Raptors");

        assertEquals(5, ranking.team_id());
        assertEquals("Cologne Raptors", ranking.team_name());
    }

    @Test
    void testEqualsAndHashCode() {
        TeamRanking ranking1 = new TeamRanking(5, "Cologne Raptors");
        TeamRanking ranking2 = new TeamRanking(5, "Cologne Raptors");

        assertEquals(ranking1, ranking2);
        assertEquals(ranking1.hashCode(), ranking2.hashCode());
    }

    @Test
    void testJsonSerializationDeserialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        TeamRanking ranking = new TeamRanking(5, "Cologne Raptors");

        String json = mapper.writeValueAsString(ranking);
        assertNotNull(json);
        assertTrue(json.contains("Cologne Raptors"));

        TeamRanking deserialized = mapper.readValue(json, TeamRanking.class);
        assertEquals(ranking, deserialized);
    }
}
