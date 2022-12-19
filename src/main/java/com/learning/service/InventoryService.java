package com.learning.service;

import com.learning.entities.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    Optional<Inventory> findInventoryById(long id);
    List<Inventory> findAllInventories();
    void createInventory(Inventory inventory);
    void updateInventoryById(Long id, Inventory inventory);
    void deleteInventoryById(Long id);
    void saveInventoriesFromExcel();
    void writeInventoriesIntoExcel();
}
