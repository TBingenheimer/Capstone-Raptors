package com.ecosystem.backend.controller;

import com.ecosystem.backend.models.Tournament;
import com.ecosystem.backend.models.Tournaments;
import com.ecosystem.backend.service.TournamentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/tournament")
public class TournamentController {

    private final TournamentService tournamentService;


    @GetMapping("/getAllTournaments")
    public Tournaments getAllTournaments(){
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/getTournament/{turnier}")
    public Tournament getTournament( @PathVariable String turnier){
        return tournamentService.findTournamentByName(turnier);
    }
}
