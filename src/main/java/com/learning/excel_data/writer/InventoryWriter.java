package com.learning.excel_data.writer;

import com.learning.entities.Inventory;
import com.learning.excel_data.reader.InventoryReader;
import com.learning.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class InventoryWriter {

    public void createInventorysheet(Workbook workbook, List<Inventory> inventoryList) throws IOException {
        String[] headers = {"Id", "Location", "Type"};


        // create a Sheet
        Sheet sheet = workbook.createSheet("Inventory");

        // method call for headerCell styling
        CellStyle headerCellStyle = getHeaderCellStyle(workbook);

        // method call for header row creation
        createHeaderRow(headers, sheet, headerCellStyle);

        // method call for data rows creation
        createDataRows(inventoryList, workbook, sheet);

        // Resize all columns to fit the content size
        for(int index = 0; index < headers.length; index++) {
            sheet.autoSizeColumn(index);
        }

        FileOutputStream fileOutputStream = new FileOutputStream(".\\resources\\poi-data.xlsx");
        workbook.write(fileOutputStream);

        fileOutputStream.close();

    }

    private static void createDataRows(List<Inventory> inventoryList, Workbook workbook, Sheet sheet) {
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        dataCellStyle.setFillForegroundColor((short) 22);
        dataCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);

        for(int rowNum = 0; rowNum < inventoryList.size(); rowNum++) {
            Row row = sheet.createRow(rowNum + 1);

            Cell zeroCell = row.createCell(0);
            zeroCell.setCellValue(inventoryList.get(rowNum).getId());
            zeroCell.setCellStyle(dataCellStyle);

            Cell firstCell = row.createCell(1);
            firstCell.setCellValue(inventoryList.get(rowNum).getLocation());
            firstCell.setCellStyle(dataCellStyle);

            Cell secondCell = row.createCell(2);
            secondCell.setCellValue(inventoryList.get(rowNum).getType().toString());
            secondCell.setCellStyle(dataCellStyle);
        }
    }

    private static void createHeaderRow(String[] headers, Sheet sheet, CellStyle headerCellStyle) {
        // create row
        Row headerRow = sheet.createRow(0);
        //  create cells
        for(int index = 0; index < headers.length; index++) {
            Cell headerCell = headerRow.createCell(index);
            headerCell.setCellValue(headers[index]);
            headerCell.setCellStyle(headerCellStyle);
        }
    }

    private static CellStyle getHeaderCellStyle(Workbook workbook) {
        // create font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.LIGHT_BLUE.getIndex());

        // create a cellstyle with font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor((short) 13);
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);

        return headerCellStyle;
    }
}
