package com.learning.controller.impl;

import com.learning.controller.AccessoryController;
import com.learning.entities.Accessory;
import com.learning.entities.Car;
import com.learning.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AccessoryControllerImpl implements AccessoryController {
    private final AccessoryService accessoryService;

    @Override
    public Accessory getAccessoryById(long id) {
        return accessoryService.findAccessoryById(id);
    }

    @Override
    public Car getCarByAccessoryId(long id) {
        return accessoryService.findCarByAccessoryId(id);
    }

    @Override
    public String writeAccessoriesIntoExcel() {
        accessoryService.writeAccessoriesIntoExcel();
        return "Accessory ExcelSheet created";
    }

    @Override
    public String createAccessory(Accessory accessory) {
        accessoryService.createAccessory(accessory);
        return "Accessory successfully created";
    }

    @Override
    public String saveAccessorysFromExcel() {
        accessoryService.saveAccessoriesFromExcel();
        return "SUCCESS";
    }

    @Override
    public String updateAccessoryById(Long id, Accessory accessory) {
        accessoryService.updateAccessoryById(id, accessory);
        return "Accessory successfully updated";
    }

    @Override
    public String deleteAccessoryById(Long id) {
        accessoryService.deleteAccessoryById(id);
        return "Accessory successfully deleted";
    }
}
