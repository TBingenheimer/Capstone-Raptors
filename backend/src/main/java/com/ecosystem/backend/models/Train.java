package com.ecosystem.backend.models;

import org.springframework.data.annotation.Id;

public record Train(
        @Id String id,
        String tournamentId,
        String userId) {
}
