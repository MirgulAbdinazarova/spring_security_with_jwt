package com.peaksoft.springsecuritywithjwt;

import com.peaksoft.springsecuritywithjwt.model.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars")
public class CarController {

    private  final CarRepository carRepository;

    @PostMapping
    @PreAuthorize("hasAuthority('VENDOR')")
    Car save (@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    List<Car> findAllCars() {
        return carRepository.findAll();
    }

    @DeleteMapping("/{carId}")
    @PreAuthorize("hasAuthority('VENDOR')")
    void delete (@PathVariable Long carId) {
        carRepository.deleteById(carId);
    }

    @GetMapping("/{carId}")
    @PreAuthorize("isAuthenticated()")
    Car findById (@PathVariable Long carId) {
     return carRepository.findById(carId).orElse(null);
    }


}
