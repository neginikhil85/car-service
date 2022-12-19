package com.learning.service.impl;

import com.learning.constants.ExceptionMessage;
import com.learning.entities.Accessory;
import com.learning.entities.Car;
import com.learning.excel_data.reader.AccessoryReader;
import com.learning.excel_data.writer.AccessoryWriter;
import com.learning.repository.AccessoryRepository;
import com.learning.service.AccessoryService;
import com.learning.service.CarService;
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
public class AccessoryServiceImpl implements AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final AccessoryReader accessoryReader;
    private final CarService carService;
    private final AccessoryWriter writer;
    private final XSSFWorkbook xssfWorkbook;

    @Override
    public Optional<Accessory> findAccessoryById(long id) {
        List<Accessory> allAccessories = accessoryRepository.findAll();
        Optional<Accessory> optionalAccessory = allAccessories.stream().filter(accessory -> accessory.getId() == id).findFirst();
        return optionalAccessory;
    }

    @Override
    public List<Accessory> findAllAccessories() {
        return accessoryRepository.findAll();
    }

    @Override
    public Optional<Car> findCarByAccessoryId(long id) {
        Optional<Accessory> optionalAccessory = findAccessoryById(id);
        if(optionalAccessory.isPresent()) {
            Accessory accessory = optionalAccessory.get();
            long carId = accessory.getCarId();
            return carService.findCarById(carId);
        }
        return Optional.empty();
    }

    @Override
    public void createAccessory(Accessory accessory) {
        accessoryRepository.save(accessory);
        log.info("Successfully saved Accessory");
    }

    @Override
    public void updateAccessoryById(Long id, Accessory accessory) {
        if(accessoryRepository.existsById(id)) {
            accessoryRepository.save(accessory);
            log.info(String.format("Successfully update Accessory with id %s", id));
        } else {
            log.error(String.format(ExceptionMessage.ACCESSORY_NOT_FOUND, id));
        }
    }

    @Override
    public void deleteAccessoryById(Long id) {
        if(accessoryRepository.existsById(id)) {
            accessoryRepository.deleteById(id);
            log.info(String.format("Successfully deleted Accessory with id %s", id));
        } else {
            log.error(String.format(ExceptionMessage.ACCESSORY_NOT_FOUND, id));
        }
    }

    @Override
    public void saveAccessoriesFromExcel() {
        List<Accessory> accessoryList = null;
        try {
            accessoryList = accessoryReader.getAccessoryObjects();
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
        }
        accessoryRepository.saveAll(accessoryList);
    }

    @Override
    public void writeAccessoriesIntoExcel() {
        try{
            writer.createAccessorySheet(xssfWorkbook,findAllAccessories());
        } catch(IOException exception){
            log.error(exception.getMessage());
        }
    }

}
