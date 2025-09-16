package com.ecosystem.backend.service;

import com.ecosystem.backend.models.Car;
import com.ecosystem.backend.repository.CarsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class CarsServiceTest {

    private CarsRepo carRepo;
    private CarsService carsService;

    @BeforeEach
    void setUp() {
        carRepo = mock(CarsRepo.class);
        carsService = new CarsService(carRepo);
    }

    @Test
    void getCarsForTournament_shouldReturnCars() {
        // given
        Car car = new Car("1","driver1","t1","3","12:00","shotgun1", Arrays.asList("u1","u2"));
        when(carRepo.findByTournamentId("t1")).thenReturn(List.of(car));

        // when
        List<Car> result = carsService.getCarsForTournament("t1");

        // then
        assertThat(result).hasSize(1).containsExactly(car);
        verify(carRepo).findByTournamentId("t1");
    }

    @Test
    void getIndividualCar_shouldReturnCar_whenExists() {
        Car car = new Car("1","driver1","t1","3","12:00","shotgun1", Arrays.asList("u1","u2"));
        when(carRepo.findById("1")).thenReturn(Optional.of(car));

        Car result = carsService.getIndividualCar("1");

        assertThat(result).isEqualTo(car);
        verify(carRepo).findById("1");
    }

    @Test
    void getIndividualCar_shouldReturnEmptyCar_whenNotExists() {
        when(carRepo.findById("99")).thenReturn(Optional.empty());

        Car result = carsService.getIndividualCar("99");

        assertThat(result.id()).isEmpty();
        assertThat(result.driverId()).isEmpty();
        assertThat(result.tournamentId()).isEmpty();
        assertThat(result.rear()).isEmpty();
    }

    @Test
    void saveCar_shouldDelegateToRepo() {
        Car car = new Car("1","driver1","t1","3","12:00","shotgun1", Collections.emptyList());
        when(carRepo.save(car)).thenReturn(car);

        Car result = carsService.saveCar(car);

        assertThat(result).isEqualTo(car);
        verify(carRepo).save(car);
    }

    @Test
    void sitDown_shouldUpdateShotgunAndRear() {
        Car oldCar = new Car("1","driver1","t1","3","12:00","oldShotgun", List.of("u1"));
        Car newCar = new Car("1","driver1","t1","3","12:00","newShotgun", List.of("u1","u2"));

        when(carRepo.findById("1")).thenReturn(Optional.of(oldCar));
        when(carRepo.save(any(Car.class))).thenAnswer(i -> i.getArgument(0));

        Car result = carsService.sitDown(newCar);

        assertThat(result.shotgun()).isEqualTo("newShotgun");
        assertThat(result.rear()).containsExactly("u1","u2");

        ArgumentCaptor<Car> captor = ArgumentCaptor.forClass(Car.class);
        verify(carRepo).save(captor.capture());
        Car savedCar = captor.getValue();
        assertThat(savedCar.shotgun()).isEqualTo("newShotgun");
    }

    @Test
    void sitDown_shouldThrow_whenCarNotFound() {
        Car newCar = new Car("99","driver1","t1","3","12:00","newShotgun", List.of("u1"));

        when(carRepo.findById("99")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> carsService.sitDown(newCar))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Car not found");
    }
}