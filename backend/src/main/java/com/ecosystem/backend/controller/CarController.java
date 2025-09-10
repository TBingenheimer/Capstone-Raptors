package com.ecosystem.backend.controller;

import com.ecosystem.backend.models.Car;
import com.ecosystem.backend.service.CarsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarController {
    private final CarsService carsService;

    @GetMapping("/getCars/{tournamentId}")
    public List<Car> getCars(@PathVariable String tournamentId) {
        return carsService.getCarsForTournament(tournamentId);
    }
}
