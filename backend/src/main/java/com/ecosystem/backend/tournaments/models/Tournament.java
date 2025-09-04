package com.ecosystem.backend.tournaments.models;

import java.util.List;

public record Tournament(String id, String name, String desctiption, String startDate, String endDate, Integer participants, List<String> riders) {
}
