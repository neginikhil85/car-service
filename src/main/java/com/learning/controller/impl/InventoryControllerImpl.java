package com.learning.controller.impl;

import com.learning.constants.ExceptionMessage;
import com.learning.controller.InventoryController;
import com.learning.entities.Inventory;
import com.learning.exceptions.InventoryNotFoundException;
import com.learning.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class InventoryControllerImpl implements InventoryController {

    private final InventoryService inventoryService;


    public Inventory getInventoryById(@PathVariable Long id) {
        Inventory inventory = null;
        try {
            inventory = inventoryService.findInventoryById(id)
                    .orElseThrow(() -> new InventoryNotFoundException(
                            String.format(ExceptionMessage.INVENTORY_NOT_FOUND, id)));
        } catch (Exception exception) {
            log.error(exception.getMessage() + exception);
        }
       return inventory;
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
