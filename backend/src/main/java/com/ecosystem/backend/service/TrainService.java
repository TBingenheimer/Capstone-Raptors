package com.ecosystem.backend.service;

import com.ecosystem.backend.models.Train;
import com.ecosystem.backend.models.User;
import com.ecosystem.backend.repository.TrainRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TrainService {
    private final TrainRepo TrainRepo;

    public Optional<List<Train>> getTrain(String tournamentId){
        return TrainRepo.findByTournamentId(tournamentId);
    }
    public void rideTrain(Train train){
        TrainRepo.save(train);
    }
    public void deleteTrain(Train train){

        TrainRepo.delete(train);
    }
}
