package com.ecosystem.backend.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public record User(String id, String displayName,String jerseyNumber, String avatar_url) {
}
