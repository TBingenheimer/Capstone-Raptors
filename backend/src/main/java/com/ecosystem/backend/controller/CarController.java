package com.ecosystem.backend.controller;

import com.ecosystem.backend.repository.CarsRepo;
import com.ecosystem.backend.models.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarsRepo carRepo;

    public CarController(CarsRepo carRepo) {
        this.carRepo = carRepo;
    }
    @GetMapping("/getCars/{turnierId}")
    public List<Car> getCars(@PathVariable String turnierId) {
        List<Car> returnCars= carRepo.findByTournamentId(turnierId);
        return returnCars;
    }
}
