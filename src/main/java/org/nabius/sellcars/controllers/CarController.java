package org.nabius.sellcars.controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.sellcars.Dto.CarDto;
import org.nabius.sellcars.services.CarsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarsService carService;


    @GetMapping
    public ResponseEntity<List<CarDto>> getCars() {
        return ResponseEntity.ok(carService.findCars());
    }

    @PostMapping
    @RolesAllowed({"USER"})
    public ResponseEntity<CarDto> addCar(@RequestParam Long userId, @Valid @RequestBody CarDto carDto) {
        return ResponseEntity.ok(carService.addCar(userId, carDto));
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"USER"})
    public ResponseEntity<Void> deleteCar(@Valid @PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @RolesAllowed({"USER"})
    public ResponseEntity<CarDto> getCar(@Valid @PathVariable Long id) {
        return ResponseEntity.of(carService.findCar(id));
    }


    @PutMapping("/{id}")
    @RolesAllowed({"USER"})
    public ResponseEntity<CarDto> modifyCar(@Valid @PathVariable Long id,@Valid @RequestBody CarDto carDto) {
        return ResponseEntity.of(carService.modifyCar(id, carDto));
    }

    @PatchMapping("/{id}")
    @RolesAllowed({"USER"})
    public ResponseEntity<CarDto> modifyCarPartially(@Valid @PathVariable Long id,@Valid @RequestBody CarDto carDto) {
        return ResponseEntity.of(carService.modifyCarPartially(id, carDto));
    }
}
