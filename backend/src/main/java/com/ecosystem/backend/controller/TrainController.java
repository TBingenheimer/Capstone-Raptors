package com.ecosystem.backend.controller;

import com.ecosystem.backend.models.Train;
import com.ecosystem.backend.service.TrainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.ecosystem.backend.models.User;

import java.util.List;

@RestController
@RequestMapping("/api/train")
@AllArgsConstructor
public class TrainController {
    private final TrainService trainService;

    @GetMapping("/getTrain/{tournamentId}")
    public List<Train> getTrain(@PathVariable String tournamentId){
        return trainService.getTrain(tournamentId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Train not found for tournament " + tournamentId
                        )
                );
    }

    @PostMapping("/rideTrain")
    public void rideTrain(@RequestBody Train train){
        trainService.rideTrain(train);
    }
    @DeleteMapping("/leaveTrain")
    public void leaveTrain(@RequestBody Train train){
        trainService.deleteTrain(train);
    }
}
