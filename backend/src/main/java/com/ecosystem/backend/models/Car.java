package com.ecosystem.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document("cars")
public record Car(
        @Id String id,
        String driverId,
        String tournamentId,
        String availableSeats,
        String takeOffTime,
        String shotgun,
        List<String> rear) {
}
