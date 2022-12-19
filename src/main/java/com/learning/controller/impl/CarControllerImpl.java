package com.learning.controller.impl;

import com.learning.controller.CarController;
import com.learning.entities.Car;
import com.learning.entities.Inventory;
import com.learning.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CarControllerImpl implements CarController {
    private final CarService carService;

    public Car getCarById(Long id) {
        return carService.findCarById(id);
    }

    public List<Car> getAllCars() {
        return carService.findAllCars();
    }

    @Override
    public String writeCarsIntoExcel() {
        carService.writeCarsIntoExcel();
        return "Car ExcelSheet created";
    }
    @Override
    public List<Car> getAllSortedCars(String sortBy) {
        return carService.getAllSortedCars(sortBy);
    }

    public Inventory getInventoryByCarId(Long id) {
        return carService.findInventoryByCarId(id);
    }

    @Override
    public String createCar(Car car) {
        carService.createCar(car);
        return "Car successfully created";
    }

    @Override
    public String saveCarsFromExcel() {
        carService.saveCarsFromExcel();
        return "SUCCESS";
    }

    @Override
    public String updateCarById(Long id, Car car) {
        carService.updateCarById(id, car);
        return "Car successfully updated";
    }

    @Override
    public String deleteCarById(Long id) {
        carService.deleteCarById(id);
        return "Car successfully deleted";
    }
}
