package com.learning.controller;

import com.learning.entities.Car;
import com.learning.entities.Inventory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cars")
public interface CarController {

    @GetMapping("/{id}") // cars/101
    Car getCarById(@PathVariable Long id);

    @GetMapping
    List<Car> getAllCars();

    @GetMapping("/excel")
    String writeCarsIntoExcel();
    @GetMapping("/sort")
    List<Car> getAllSortedCars(@RequestParam(value ="sortBy", required = false, defaultValue = "") String sortBy);

    @GetMapping("/inventory/{id}")
    Inventory getInventoryByCarId(@PathVariable Long id);

    @PostMapping
    String createCar(@RequestBody Car car);
    @PostMapping("/excel")
    String saveCarsFromExcel();

    @PutMapping("/{id}")
    String updateCarById(@PathVariable Long id,@RequestBody Car car);
    @DeleteMapping("/{id}")
    String deleteCarById(@PathVariable Long id);
}
