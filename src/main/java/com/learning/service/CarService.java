package com.learning.service;

import com.learning.entities.Car;
import com.learning.entities.Inventory;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface CarService {

    Car findCarById(Long id);
    List<Car> findAllCars();
    List<Car> getAllSortedCars(String sortBy);
    Inventory findInventoryByCarId(Long id);
    void createCar(Car car);
    void updateCarById(Long id, Car car);
    void deleteCarById(Long id);
    void saveCarsFromExcel();
    void writeCarsIntoExcel();



}
