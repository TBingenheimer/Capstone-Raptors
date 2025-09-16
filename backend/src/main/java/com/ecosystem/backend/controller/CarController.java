package com.ecosystem.backend.controller;

import com.ecosystem.backend.models.Car;
import com.ecosystem.backend.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarController {
    private final CarsService carsService;

    @GetMapping("/getCars/{tournamentId}")
    public List<Car> getCars(@PathVariable String tournamentId) {
        return carsService.getCarsForTournament(tournamentId);
    }
    @GetMapping("/getCar/{carId}")
    public Car getCar(@PathVariable String carId) {
        return carsService.getIndividualCar(carId);
    }

    @PostMapping("/createCar")
    public Car createCar(@RequestBody Car car) {
        return carsService.saveCar(car);
    }
    @PutMapping("/updatePassengers")
    public Car updatePassengers(@RequestBody Car car) {
        return carsService.sitDown(car);
    }
}
