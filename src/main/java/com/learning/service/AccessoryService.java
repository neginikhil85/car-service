package com.learning.service;

import com.learning.entities.Accessory;
import com.learning.entities.Car;

import java.util.List;

public interface AccessoryService {

    Accessory findAccessoryById(Long id);
    List<Accessory> findAllAccessories();
    Car findCarByAccessoryId(Long id);
    void createAccessory(Accessory accessory);
    void updateAccessoryById(Long id, Accessory accessory);
    void deleteAccessoryById(Long id);
    void saveAccessoriesFromExcel();
    void writeAccessoriesIntoExcel();
}
