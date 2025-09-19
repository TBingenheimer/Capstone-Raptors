package com.ecosystem.backend.service;

import com.ecosystem.backend.models.Train;
import com.ecosystem.backend.repository.TrainRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class TrainServiceTest {

    private TrainRepo trainRepo;
    private TrainService trainService;

    @BeforeEach
    void setUp() {
        trainRepo = mock(TrainRepo.class);
        trainService = new TrainService(trainRepo);
    }

    @Test
    void getTrain_shouldReturnList_whenExists() {
        Train train = new Train("1", "tournament1", "user1");
        when(trainRepo.findByTournamentId("tournament1"))
                .thenReturn(Optional.of(List.of(train)));

        Optional<List<Train>> result = trainService.getTrain("tournament1");

        assertThat(result).isPresent();
        assertThat(result.get()).hasSize(1);
        assertThat(result.get().get(0).tournamentId()).isEqualTo("tournament1");
        verify(trainRepo).findByTournamentId("tournament1");
    }

    @Test
    void getTrain_shouldReturnEmpty_whenNotFound() {
        when(trainRepo.findByTournamentId("tournamentX")).thenReturn(Optional.empty());

        Optional<List<Train>> result = trainService.getTrain("tournamentX");

        assertThat(result).isEmpty();
        verify(trainRepo).findByTournamentId("tournamentX");
    }

    @Test
    void rideTrain_shouldCallSave() {
        Train train = new Train("1", "tournament1", "user1");

        trainService.rideTrain(train);

        ArgumentCaptor<Train> captor = ArgumentCaptor.forClass(Train.class);
        verify(trainRepo).save(captor.capture());
        assertThat(captor.getValue()).isEqualTo(train);
    }

    @Test
    void deleteTrain_shouldCallDelete() {
        Train train = new Train("1", "tournament1", "user1");

        trainService.deleteTrain(train);

        ArgumentCaptor<Train> captor = ArgumentCaptor.forClass(Train.class);
        verify(trainRepo).delete(captor.capture());
        assertThat(captor.getValue()).isEqualTo(train);
    }
}
