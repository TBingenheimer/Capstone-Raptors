package com.ecosystem.backend.repository;

import com.ecosystem.backend.models.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarsRepo extends MongoRepository<Car,String> {
    List<Car> findByTournamentId(String tournamentId);
}
