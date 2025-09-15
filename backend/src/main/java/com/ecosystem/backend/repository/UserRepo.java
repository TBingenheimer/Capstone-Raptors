package com.ecosystem.backend.repository;

import com.ecosystem.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User,String> {
    Optional<User> findByGitHubId(String gitHubId);
}
