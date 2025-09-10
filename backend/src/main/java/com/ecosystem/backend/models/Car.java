package com.ecosystem.backend.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document("cars")
public record Car(
        String driverId,
        String tournamentId,
        String availableSeats,
        String takeOffTime,
        String shotgun,
        List<String> rear) {
}
