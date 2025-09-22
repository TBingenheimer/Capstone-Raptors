package com.ecosystem.backend.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    @Test
    void testMatchCreation() {
        Match match = new Match(
                "t1",
                "team1",
                "team2",
                "team1",
                "Finale",
                "TurnierX",
                "Berlin",
                "Team Alpha",
                "Team Beta",
                "Team Alpha",
                "best of 3",
                "2",
                "1",
                "10",
                "8",
                "2:1",
                "A",
                "g1"
        );

        assertEquals("t1", match.tournament_id());
        assertEquals("team1", match.first_team_id());
        assertEquals("team2", match.second_team_id());
        assertEquals("team1", match.victorious_team_id());
        assertEquals("Finale", match.name());
        assertEquals("TurnierX", match.tournament());
        assertEquals("Berlin", match.city());
        assertEquals("Team Alpha", match.first_team());
        assertEquals("Team Beta", match.second_team());
        assertEquals("Team Alpha", match.victorious_team());
        assertEquals("best of 3", match.mode());
        assertEquals("2", match.sets_first_team());
        assertEquals("1", match.sets_second_team());
        assertEquals("10", match.points_first_team());
        assertEquals("8", match.points_second_team());
        assertEquals("2:1", match.score_total());
        assertEquals("A", match.group());
        assertEquals("g1", match.group_id());
    }

    @Test
    void testEqualsAndHashCode() {
        Match match1 = new Match("t1","team1","team2","team1","Finale","TurnierX","Berlin",
                "Team Alpha","Team Beta","Team Alpha","best of 3","2","1","10","8","2:1","A","g1");
        Match match2 = new Match("t1","team1","team2","team1","Finale","TurnierX","Berlin",
                "Team Alpha","Team Beta","Team Alpha","best of 3","2","1","10","8","2:1","A","g1");

        assertEquals(match1, match2);
        assertEquals(match1.hashCode(), match2.hashCode());
    }

    @Test
    void testJsonSerializationDeserialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Match match = new Match("t1","team1","team2","team1","Finale","TurnierX","Berlin",
                "Team Alpha","Team Beta","Team Alpha","best of 3","2","1","10","8","2:1","A","g1");

        String json = mapper.writeValueAsString(match);
        assertNotNull(json);
        assertTrue(json.contains("TurnierX"));

        Match deserialized = mapper.readValue(json, Match.class);
        assertEquals(match, deserialized);
    }
}
