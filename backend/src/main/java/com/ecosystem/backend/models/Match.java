package com.ecosystem.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Match(
        String tournament_id,
        String first_team_id,
        String second_team_id,
        String victorious_team_id,
        String name,
        String tournament,
        String city,
        String first_team,
        String second_team,
        String victorious_team,
        String mode,
        String sets_first_team,
        String sets_second_team,
        String points_first_team,
        String points_second_team,
        String score_total,
        String group,
        String group_id
) {
}
