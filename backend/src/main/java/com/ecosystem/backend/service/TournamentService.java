package com.ecosystem.backend.service;

import com.ecosystem.backend.models.Tournament;
import com.ecosystem.backend.models.Tournaments;
import com.ecosystem.backend.models.User;
import com.ecosystem.backend.repository.TournamentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
@AllArgsConstructor
public class TournamentService {
    private final TournamentRepo tournamentRepo;

    public Tournaments getAllTournaments(){
        List<Tournament> list = tournamentRepo.findAll();
        if (list.isEmpty()) {
            Tournament defaultTournament = new Tournament(
                    "0",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "-",
                    "-",
                    0
            );
            List<Tournament> defaultList = Arrays.asList(defaultTournament);
            System.out.println(defaultList);
            return new Tournaments(defaultList.toArray(new Tournament[0]));
        }


        return new Tournaments(list.toArray(new Tournament[0]));
    }

    public Tournament findTournamentByName(String tournamentName){
        Tournament tournament = tournamentRepo.findTournamentByName(tournamentName);
        if (tournament == null) {
            return new Tournament("0", "","","","", "", "-", "-", 0);
        }

        return tournament;
    }

    public Tournament createTournament(Tournament tournamentData){
        return tournamentRepo.save(tournamentData);
    }
}
