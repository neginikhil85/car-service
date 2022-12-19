package com.learning.service.impl;

import com.learning.constants.ExceptionMessage;
import com.learning.entities.Inventory;
import com.learning.excel_data.reader.InventoryReader;
import com.learning.excel_data.writer.InventoryWriter;
import com.learning.repository.InventoryRepository;
import com.learning.service.InventoryService;
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
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository; // immutable object
    private final InventoryReader reader;
    private final InventoryWriter writer;

    private final XSSFWorkbook xssfWorkbook;

    @Override
    public Optional<Inventory> findInventoryById(long id) {
        return inventoryRepository.findAll().stream()
                .filter(inventory -> inventory.getId() == id).findFirst();
    }

    @Override
    public List<Inventory> findAllInventories() {
        return inventoryRepository.findAll();
    }

    @Override
    public void createInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
        log.info("Successfully saved Inventory");
    }

    @Override
    public void updateInventoryById(Long id, Inventory inventory) {
        if(inventoryRepository.existsById(id)) {
            inventoryRepository.save(inventory);
            log.info(String.format("Successfully update Inventory with id %s", id));
        } else {
            log.error(String.format(ExceptionMessage.INVENTORY_NOT_FOUND, id));
        }
    }

    @Override
    public void deleteInventoryById(Long id) {
        if(inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            log.info(String.format("Successfully update Inventory with id %s", id));
        } else {
            log.error(String.format(ExceptionMessage.INVENTORY_NOT_FOUND, id));
        }
    }

    @Override
    public void saveInventoriesFromExcel() {
        List<Inventory> inventoryList = null;
        try {
            inventoryList = reader.getInventoryObjects();
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
        }
        inventoryRepository.saveAll(inventoryList);
    }

    @Override
    public void writeInventoriesIntoExcel() {
        try {
            writer.createInventorysheet(xssfWorkbook, findAllInventories());
        } catch(IOException exception) {
            log.error(exception.getMessage());
        }
    }


}
