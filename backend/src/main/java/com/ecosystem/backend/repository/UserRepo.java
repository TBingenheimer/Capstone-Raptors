package com.ecosystem.backend.repository;

import com.ecosystem.backend.tournaments.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
}
