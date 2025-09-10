package com.ecosystem.backend.service;

import com.ecosystem.backend.models.Car;
import com.ecosystem.backend.repository.CarsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CarsService {
    private final CarsRepo carRepo;

    public List<Car> getCarsForTournament(String tournamentId){
        List<Car> cars= carRepo.findByTournamentId(tournamentId);
        if (cars.isEmpty()) {
            Car defaultCar = new Car(
                    "0",                    // driverId
                    tournamentId,           // tournamentId
                    "0",                    // availableSeats
                    "n/a",                  // takeOffTime
                    "",
                    List.of("")     // riders (Platzhalter)
            );
            return List.of(defaultCar);
        }
        return cars;
    }
}
