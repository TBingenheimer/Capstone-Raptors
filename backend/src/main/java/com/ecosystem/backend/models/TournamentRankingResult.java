package com.ecosystem.backend.models;

import java.util.Map;

public record TournamentRankingResult(
        int tournament_id,
        String tournament_name,
        Map<String, TeamRanking> rankings
) {}