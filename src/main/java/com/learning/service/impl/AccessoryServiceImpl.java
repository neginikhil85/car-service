package com.learning.service.impl;

import com.learning.constants.ExceptionMessage;
import com.learning.entities.Accessory;
import com.learning.entities.Car;
import com.learning.excel_data.reader.AccessoryReader;
import com.learning.excel_data.writer.AccessoryWriter;
import com.learning.exceptions.AccessoryNotFoundException;
import com.learning.repository.AccessoryRepository;
import com.learning.service.AccessoryService;
import com.learning.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
    public Accessory findAccessoryById(Long id) {
        return accessoryRepository.findById(id)
                .orElseThrow(() -> new AccessoryNotFoundException(ExceptionMessage.ACCESSORY_NOT_FOUND));
    }

    @Override
    public List<Accessory> findAllAccessories() {
        return accessoryRepository.findAll();
    }

    @Override
    public Car findCarByAccessoryId(Long id) {
       return carService.findCarById(findAccessoryById(id).getCarId());
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
