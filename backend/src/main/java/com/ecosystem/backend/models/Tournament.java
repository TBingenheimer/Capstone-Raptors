package com.ecosystem.backend.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public record Tournament(
        @Id String id,
        String name,
        String description,
        String street,
        String zip,
        String city,
        String startDateTime,
        String endDateTime,
        Integer participants,
        String tugenyId) {
}
