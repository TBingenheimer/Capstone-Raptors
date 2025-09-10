package com.ecosystem.backend.controller;

import com.ecosystem.backend.repository.TournamentRepo;
import com.ecosystem.backend.models.Tournament;
import com.ecosystem.backend.models.Tournaments;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/tournament")
public class TournamentController {

    private final TournamentRepo tournamentRepo;

    public TournamentController(TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
    }

    @GetMapping("/getAllTournaments")
    public Tournaments getAllTournaments(){
        List<Tournament> list = tournamentRepo.findAll();
        System.out.println(list);
        return new Tournaments(list.toArray(new Tournament[0]));
    }

    @GetMapping("/getTournament/{turnier}")
    public Tournament getTournament( @PathVariable String turnier){
        return tournamentRepo.findTournamentByName(turnier);
    }
}
