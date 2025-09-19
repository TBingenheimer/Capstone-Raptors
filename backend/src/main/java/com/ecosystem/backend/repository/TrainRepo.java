package com.ecosystem.backend.repository;
import com.ecosystem.backend.models.Train;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TrainRepo extends MongoRepository<Train,String> {
    Optional<List<Train>> findByTournamentId(String tournamentId);
}
