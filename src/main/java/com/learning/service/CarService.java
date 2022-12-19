package com.learning.service;

import com.learning.entities.Car;
import com.learning.entities.Inventory;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Optional<Car> findCarById(Long id);
    List<Car> findAllCars();
    Optional<Inventory> findInventoryByCarId(Long id);
    void createCar(Car car);
    void updateCarById(Long id, Car car);
    void deleteCarById(Long id);
    void saveCarsFromExcel();
    void writeCarsIntoExcel();



}
