package com.ecosystem.backend.controller;

import com.ecosystem.backend.tournaments.models.Tournament;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@RestController
@RequestMapping("/api/tournament")
public class TournamentController {
    @GetMapping("/getAllTournaments")
    public Map<String,Tournament> getAllTournaments(){
        Tournament tournament1 = new Tournament("1","9. Turnier zu Münster","Tolles Turnier in Münster","2025-07-27","2025-07-29",7, Arrays.asList("2","3","4","5","6"));
        Tournament tournament2 = new Tournament("2","4. Bochumer Sommerturnier","Tolles Turnier in Bpochum","2025-08-29","2025-08-31",7, Arrays.asList("2","3","4","5","6"));


        Map<String, Tournament> tournaments = new TreeMap();
        tournaments.put("1",tournament1);
        tournaments.put("2",tournament2);

        return tournaments;
    }
}
