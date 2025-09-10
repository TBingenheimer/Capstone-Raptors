package com.ecosystem.backend.repository;

import com.ecosystem.backend.models.Tournament;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepo extends MongoRepository<Tournament,String> {
    Tournament findTournamentByName(String name);

}
