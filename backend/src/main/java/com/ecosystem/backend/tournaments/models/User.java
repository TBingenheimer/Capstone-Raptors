package com.ecosystem.backend.tournaments.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public record User(String id, String displayName,String jesrseyNumber, String avatar_url) {
}
