package com.ecosystem.backend.service;

import com.ecosystem.backend.models.Tournament;
import com.ecosystem.backend.models.Tournaments;
import com.ecosystem.backend.repository.TournamentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TournamentServiceTest {

    private TournamentRepo tournamentRepo;
    private TournamentService tournamentService;

    @BeforeEach
    void setUp() {
        tournamentRepo = mock(TournamentRepo.class);
        tournamentService = new TournamentService(tournamentRepo);
    }

    @Test
    void getAllTournaments_shouldReturnTournaments_whenRepoNotEmpty() {
        Tournament t1 = new Tournament("1", "Deutsche Meisterschaft", "Juhu wir fahren nach Berlin", "2025-01-01", "12345", "Berlin", "-", "-", 10);
        Tournament t2 = new Tournament("2", "Westfälische Meisterschaft", "Ab nach Bonn", "2025-02-01", "6589", "Bonn", "-", "-", 20);
        when(tournamentRepo.findAll()).thenReturn(List.of(t1, t2));

        Tournaments result = tournamentService.getAllTournaments();

        assertThat(result.tournaments()).containsExactly(t1, t2);
        verify(tournamentRepo).findAll();
    }

    @Test
    void getAllTournaments_shouldReturnDefaultTournament_whenRepoEmpty() {
        when(tournamentRepo.findAll()).thenReturn(List.of());

        Tournaments result = tournamentService.getAllTournaments();

        assertThat(result.tournaments()).hasSize(1);
        Tournament defaultTournament = result.tournaments().get(0);
        assertThat(defaultTournament.id()).isEqualTo("0");
        assertThat(defaultTournament.name()).isEmpty();
    }

    @Test
    void findTournamentByName_shouldReturnTournament_whenExists() {
        Tournament t = new Tournament("1", "Summer Cup", "Dolles Turnier", "2025-01-01", "45677", "Bochum", "-", "-", 16);
        when(tournamentRepo.findTournamentByName("Summer Cup")).thenReturn(t);

        Tournament result = tournamentService.findTournamentByName("Summer Cup");

        assertThat(result).isEqualTo(t);
        verify(tournamentRepo).findTournamentByName("Summer Cup");
    }

    @Test
    void findTournamentByName_shouldReturnDefault_whenNotExists() {
        when(tournamentRepo.findTournamentByName("Unknown")).thenReturn(null);

        Tournament result = tournamentService.findTournamentByName("Unknown");

        assertThat(result.id()).isEqualTo("0");
        assertThat(result.name()).isEqualTo("");
    }

    @Test
    void createTournament_shouldSaveAndReturnTournament() {
        Tournament newTournament = new Tournament("1", "Bergische Meisterschaft", "Schön hier", "2025-03-01", "88888", "Whoppertal", "-", "-", 8);
        when(tournamentRepo.save(newTournament)).thenReturn(newTournament);

        Tournament result = tournamentService.createTournament(newTournament);

        assertThat(result).isEqualTo(newTournament);
        verify(tournamentRepo).save(newTournament);
    }
}