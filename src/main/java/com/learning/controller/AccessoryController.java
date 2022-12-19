package com.learning.controller;

import com.learning.entities.Accessory;
import com.learning.entities.Car;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/accessories")
public interface AccessoryController {

    @GetMapping("/{id}") // accessories/2000
    Accessory getAccessoryById(@PathVariable Long id);

    @GetMapping("/car/{id}") // accessories/car/2000
    Car getCarByAccessoryId(@PathVariable Long id);

    @GetMapping("/excel")
    String writeAccessoriesIntoExcel();

    @PostMapping
    String createAccessory(@RequestBody Accessory accessory);
    @PostMapping("/excel")
    String saveAccessorysFromExcel();
    @PutMapping("/{id}")
    String updateAccessoryById(@PathVariable Long id, @RequestBody Accessory accessory);
    @DeleteMapping("/{id}")
    String deleteAccessoryById(@PathVariable Long id);
}
