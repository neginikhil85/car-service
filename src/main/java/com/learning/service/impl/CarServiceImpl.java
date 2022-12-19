package com.learning.service.impl;

import com.learning.constants.ExceptionMessage;
import com.learning.entities.Car;
import com.learning.entities.Inventory;
import com.learning.excel_data.reader.CarReader;
import com.learning.excel_data.writer.CarWriter;
import com.learning.exceptions.CarNotFoundException;
import com.learning.repository.CarRepository;
import com.learning.service.CarService;
import com.learning.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarReader carReader;
    private final InventoryService inventoryService;
    private final CarWriter carWriter;
    private final XSSFWorkbook xssfWorkbook;

    public Optional<Car> findCarById(Long id) {
        return carRepository.findAll()
                .stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Optional<Inventory> findInventoryByCarId(Long id) {
        return  inventoryService.findInventoryById((findCarById(id))
                .orElseThrow(() -> new CarNotFoundException(String.format(ExceptionMessage.CAR_NOT_FOUND)))
                .getInventoryId());
    }

    @Override
    public void createCar(Car car) {
        carRepository.save(car);
        log.info("Successfully saved Car");
    }

    @Override
    public void updateCarById(Long id, Car car) {
        if(carRepository.existsById(id)){
            carRepository.save(car);
            log.info(String.format("Successfully update Car with id %s", id));
        } else {
            log.error(String.format(ExceptionMessage.CAR_NOT_FOUND, id));
        }
    }

    @Override
    public void deleteCarById(Long id) {
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
            log.info(String.format("Successfully deleted Car with id %s", id));
        } else {
            log.error(String.format(ExceptionMessage.CAR_NOT_FOUND, id));
        }
    }

    @Override
    public void saveCarsFromExcel() {
        List<Car> carList = null;
        try {
            carList = carReader.getCarObjects();
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
        }
        carRepository.saveAll(carList);
    }

    @Override
    public void writeCarsIntoExcel() {
        try{
            carWriter.createCarsheet(xssfWorkbook, findAllCars());
        }catch(IOException exception) {
            log.error(exception.getMessage());
        }
    }
}
