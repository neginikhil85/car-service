package com.learning.controller.impl;

import com.learning.controller.InventoryController;
import com.learning.entities.Inventory;
import com.learning.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InventoryControllerImpl implements InventoryController {

    private final InventoryService inventoryService;


    public Inventory getInventoryById(Long id) {
        return inventoryService.findInventoryById(id);
    }

    @Override
    public String writeInventoriesIntoExcel() {
        inventoryService.writeInventoriesIntoExcel();
        return "Inventory ExcelSheet created";
    }

    @Override
    public String createInventory(Inventory inventory) {
        inventoryService.createInventory(inventory);
        return "Inventory successfully created";
    }

    @Override
    public String saveInventoriesFromExcel() {
        inventoryService.saveInventoriesFromExcel();
        return "SUCCESS";
    }

    @Override
    public String updateInventoryById(Long id, Inventory inventory) {
        inventoryService.updateInventoryById(id, inventory);
        return "Inventory successfully updated";
    }

    @Override
    public String deleteInventoryById(Long id) {
        inventoryService.deleteInventoryById(id);
        return "Inventory successfully deleted";
    }

}
