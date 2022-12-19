package com.learning.controller.impl;

import com.learning.constants.ExceptionMessage;
import com.learning.controller.CarController;
import com.learning.entities.Car;
import com.learning.entities.Inventory;
import com.learning.exceptions.CarNotFoundException;
import com.learning.exceptions.InventoryNotFoundException;
import com.learning.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CarControllerImpl implements CarController {
    private final CarService carService;

    public Car getCarById(@PathVariable long id) {
        Car car = null;
        try {
            car = carService.findCarById(id)
                    .orElseThrow(() -> new CarNotFoundException(String.format(ExceptionMessage.CAR_NOT_FOUND, id)));
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception);
        }
        return car;
    }

    public List<Car> getAllCars() {
        return carService.findAllCars();
    }

    @Override
    public String writeCarsIntoExcel() {
        carService.writeCarsIntoExcel();
        return "Car ExcelSheet created";
    }

    public List<Car> getAllSortedCars(@RequestParam(value = "sortBy", required = false, defaultValue = "") String sortBy) {

        Comparator<Car> comparator;

        switch (sortBy) {
            case "name":
                comparator = (car1, car2) -> car1.getName().compareTo(car2.getName());
                break;
            case "modelNo":
                comparator = (car1, car2) -> car1.getModelNo().compareTo(car2.getModelNo());
                break;
            case "brand":
                comparator = (car1, car2) -> car1.getBrand().compareTo(car2.getBrand());
                break;
            default:
                comparator = (car1, car2) -> car1.getId().compareTo(car2.getId());
        }
        return carService.findAllCars().stream()
                .sorted(comparator) // sorted list
                .collect(Collectors.toList());

    }

    public Inventory getInventoryByCarId(@PathVariable long id) {
        Inventory inventory = null;
        try {
            inventory = carService.findInventoryByCarId(id)
                    .orElseThrow(() -> new InventoryNotFoundException(ExceptionMessage.INVENTORY_NOT_FOUND + id));

        } catch (Exception exception) {
            log.error(exception.getMessage() + exception);
        }
        return inventory;
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
