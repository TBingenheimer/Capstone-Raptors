package com.ecosystem.backend.service;

import com.ecosystem.backend.models.Car;
import com.ecosystem.backend.repository.CarsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CarsService {
    private final CarsRepo carRepo;

    public List<Car> getCarsForTournament(String tournamentId){
        List<Car> cars= carRepo.findByTournamentId(tournamentId);
        return cars;
    }
    public Car getIndividualCar(String carId){
        Car returnCar;
        Car car = carRepo.findById(carId).orElse(null);
        if (car == null) {
            returnCar = new Car("","","","","","", Arrays.asList());
        }else{
            returnCar = car;
        }
        return returnCar;
    }

    public Car saveCar(Car car){
        return carRepo.save(car);
    }
    public Car sitDown(@RequestBody Car car){
        Car oldCar = carRepo.findById(car.id())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Car updatedCar = new Car(
                oldCar.id(),
                oldCar.driverId(),
                oldCar.tournamentId(),
                oldCar.availableSeats(),
                oldCar.takeOffTime(),
                car.shotgun(),          // nur das Feld geändert
                car.rear()
        );
        carRepo.save(updatedCar);
        return updatedCar;
    }
    public void deleteCar(String id){
        carRepo.deleteById(id);
    }
}
