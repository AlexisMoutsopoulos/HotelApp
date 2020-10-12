package gr.aueb.softeng.athenaumgranthotel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Alexis on 13/9/2018.
 * Read data from excel to create chart
 */

public class ReadFromExcel {

    public int returnWaters(String filepath) throws IOException {

        FileInputStream fInputStream = new FileInputStream(filepath.trim());

				/* Create the workbook object. */
        Workbook excelWookBook = new HSSFWorkbook(fInputStream);

				/* Get the sheet by name. */
        Sheet sheet = excelWookBook.getSheet("myOrder");
        int lastRowNum = sheet.getLastRowNum();

        Row row = sheet.getRow(lastRowNum);


        int waterCell = row.getFirstCellNum() + 6 ;



        return (int) row.getCell(waterCell).getNumericCellValue();

    }
    public int returnWWines(String filepath) throws IOException {

        FileInputStream fInputStream = new FileInputStream(filepath.trim());

				/* Create the workbook object. */
        Workbook excelWookBook = new HSSFWorkbook(fInputStream);

				/* Get the sheet by name. */
        Sheet sheet = excelWookBook.getSheet("myOrder");
        int lastRowNum = sheet.getLastRowNum();

        Row row = sheet.getRow(lastRowNum);


        int waterCell = row.getFirstCellNum() + 7 ;



        return (int) row.getCell(waterCell).getNumericCellValue();

    }
    public int returnRWines(String filepath) throws IOException {

        FileInputStream fInputStream = new FileInputStream(filepath.trim());

				/* Create the workbook object. */
        Workbook excelWookBook = new HSSFWorkbook(fInputStream);

				/* Get the sheet by name. */
        Sheet sheet = excelWookBook.getSheet("myOrder");
        int lastRowNum = sheet.getLastRowNum();

        Row row = sheet.getRow(lastRowNum);


        int waterCell = row.getFirstCellNum() + 8 ;



        return (int) row.getCell(waterCell).getNumericCellValue();

    }
    public int returnChips(String filepath) throws IOException {

        FileInputStream fInputStream = new FileInputStream(filepath.trim());

				/* Create the workbook object. */
        Workbook excelWookBook = new HSSFWorkbook(fInputStream);

				/* Get the sheet by name. */
        Sheet sheet = excelWookBook.getSheet("myOrder");
        int lastRowNum = sheet.getLastRowNum();

        Row row = sheet.getRow(lastRowNum);


        int waterCell = row.getFirstCellNum() + 9 ;



        return (int) row.getCell(waterCell).getNumericCellValue();

    }

    public int returnCola(String filepath) throws IOException {

        FileInputStream fInputStream = new FileInputStream(filepath.trim());

				/* Create the workbook object. */
        Workbook excelWookBook = new HSSFWorkbook(fInputStream);

				/* Get the sheet by name. */
        Sheet sheet = excelWookBook.getSheet("myOrder");
        int lastRowNum = sheet.getLastRowNum();

        Row row = sheet.getRow(lastRowNum);


        int waterCell = row.getFirstCellNum() + 1 ;



        return (int) row.getCell(waterCell).getNumericCellValue();

    }

    public int returnFanta(String filepath) throws IOException {

        FileInputStream fInputStream = new FileInputStream(filepath.trim());

				/* Create the workbook object. */
        Workbook excelWookBook = new HSSFWorkbook(fInputStream);

				/* Get the sheet by name. */
        Sheet sheet = excelWookBook.getSheet("myOrder");
        int lastRowNum = sheet.getLastRowNum();

        Row row = sheet.getRow(lastRowNum);


        int waterCell = row.getFirstCellNum() +2 ;



        return (int) row.getCell(waterCell).getNumericCellValue();

    }

    public int returnLight(String filepath) throws IOException {

        FileInputStream fInputStream = new FileInputStream(filepath.trim());

				/* Create the workbook object. */
        Workbook excelWookBook = new HSSFWorkbook(fInputStream);

				/* Get the sheet by name. */
        Sheet sheet = excelWookBook.getSheet("myOrder");
        int lastRowNum = sheet.getLastRowNum();

        Row row = sheet.getRow(lastRowNum);


        int waterCell = row.getFirstCellNum() +3 ;



        return (int) row.getCell(waterCell).getNumericCellValue();

    }

    public int returnZero(String filepath) throws IOException {

        FileInputStream fInputStream = new FileInputStream(filepath.trim());

				/* Create the workbook object. */
        Workbook excelWookBook = new HSSFWorkbook(fInputStream);

				/* Get the sheet by name. */
        Sheet sheet = excelWookBook.getSheet("myOrder");
        int lastRowNum = sheet.getLastRowNum();

        Row row = sheet.getRow(lastRowNum);


        int waterCell = row.getFirstCellNum() +4 ;



        return (int) row.getCell(waterCell).getNumericCellValue();

    }

    public int returnSoda(String filepath) throws IOException {

        FileInputStream fInputStream = new FileInputStream(filepath.trim());

				/* Create the workbook object. */
        Workbook excelWookBook = new HSSFWorkbook(fInputStream);

				/* Get the sheet by name. */
        Sheet sheet = excelWookBook.getSheet("myOrder");
        int lastRowNum = sheet.getLastRowNum();

        Row row = sheet.getRow(lastRowNum);


        int waterCell = row.getFirstCellNum() +5 ;



        return (int) row.getCell(waterCell).getNumericCellValue();

    }
}
