package com.learning.controller;

import com.learning.entities.Inventory;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/inventories")
public interface InventoryController {
    @GetMapping("/{id}")
    Inventory getInventoryById(@PathVariable Long id);
    @GetMapping("/excel")
    String writeInventoriesIntoExcel();

    @PostMapping
    String createInventory(@RequestBody Inventory inventory);

    @PostMapping("/excel")
    String saveInventoriesFromExcel();
    @PutMapping("/{id}")
    String updateInventoryById(@PathVariable Long id,@RequestBody Inventory inventory);
    @DeleteMapping("/{id}")
    String deleteInventoryById(@PathVariable Long id);
}
