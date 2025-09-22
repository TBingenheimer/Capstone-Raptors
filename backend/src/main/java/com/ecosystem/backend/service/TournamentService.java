package com.ecosystem.backend.service;

import com.ecosystem.backend.models.*;
import com.ecosystem.backend.repository.TournamentRepo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class TournamentService {

    private final TournamentRepo tournamentRepo;
    private final RestClient restClient;

    public TournamentService(TournamentRepo tournamentRepo) {
        this.tournamentRepo = tournamentRepo;
        this.restClient = RestClient.builder().build(); // nur hier im Service
    }

    public Tournaments getAllTournaments() {
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
                    0,
                    ""
            );
            return new Tournaments(List.of(defaultTournament));
        }

        return new Tournaments(list);
    }

    public Tournament findTournamentByName(String tournamentName) {
        Tournament tournament = tournamentRepo.findTournamentByName(tournamentName);
        if (tournament == null) {
            return new Tournament("0", "", "", "", "", "", "-", "-", 0, "");
        }

        return tournament;
    }

    public Tournament createTournament(Tournament tournamentData) {
        return tournamentRepo.save(tournamentData);
    }

    public TugenyResult getTugenyResult() {
        String url = "https://tugeny.org/api/persistent/matches?teamIds=5&returnType=json";

        List<Match> matches = restClient.get()
                .uri(url)
                .retrieve()
                .body(new ParameterizedTypeReference<List<Match>>() {});

        return new TugenyResult(matches);
    }

    public TournamentRankingResult getTournamentRankings(String tugneyId) {
        String url = "https://tugeny.org/api/persistent/rankingsByTournamentId/"+tugneyId+"?returnType=json";

        return restClient.get()
                .uri(url)
                .retrieve()
                .body(new ParameterizedTypeReference<TournamentRankingResult>() {});
    }


}
