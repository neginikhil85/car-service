package com.learning.excel_data.reader;

import com.learning.entities.Inventory;
import com.learning.enums.InventoryType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class InventoryReader {

    public List<Inventory> getInventoryObjects() throws IOException {
        FileInputStream file = new FileInputStream(new File(".\\resources\\data.xlsx"));
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        List<Inventory> inventoryList = new ArrayList<>();

        //I've Header and I'm ignoring header for that I've +1 in loop
        getInventoryList(sheet, inventoryList);
        file.close();
        return inventoryList;
    }

    private static void getInventoryList(XSSFSheet sheet, List<Inventory> inventoryList) {
        for (int index = sheet.getFirstRowNum() + 1; index <= sheet.getLastRowNum(); index++) {
            Row row = sheet.getRow(index);
            Inventory inventory = Inventory.builder().build();

            for (int index2 = row.getFirstCellNum(); index2 < row.getLastCellNum(); index2++) {
                Cell cell = row.getCell(index2);
                if (index2 == 0) {
                    inventory.setId((long) cell.getNumericCellValue());
                } else if (index2 == 1) {
                    inventory.setLocation(cell.getStringCellValue());
                } else if (index2 == 2) {
                   inventory.setType(InventoryType.valueOf(cell.getStringCellValue()));
                } else {
                    log.error("Data not found ");
                }
            }
            inventoryList.add(inventory);
        }
    }
}