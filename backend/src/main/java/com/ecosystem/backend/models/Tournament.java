package com.ecosystem.backend.models;

import java.util.List;

public record Tournament(
        String id,
        String name,
        String description,
        String startDate,
        String endDate,
        Integer participants) {
}
