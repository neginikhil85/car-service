package com.learning.service;

import com.learning.entities.Accessory;
import com.learning.entities.Car;

import java.util.List;
import java.util.Optional;

public interface AccessoryService {

    Optional<Accessory> findAccessoryById(long id);
    List<Accessory> findAllAccessories();
    Optional<Car> findCarByAccessoryId(long id);
    void createAccessory(Accessory accessory);
    void updateAccessoryById(Long id, Accessory accessory);
    void deleteAccessoryById(Long id);
    void saveAccessoriesFromExcel();
    void writeAccessoriesIntoExcel();
}
