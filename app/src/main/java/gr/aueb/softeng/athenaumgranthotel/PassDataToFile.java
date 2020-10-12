package gr.aueb.softeng.athenaumgranthotel;


import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;



import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Alexis on 6/9/2018.
 * This class is responsible to save data to excel file in DeviceStorage / DCIM / Newfolder
 */

public class PassDataToFile extends AppCompatActivity {


    ArrayList<Room> rooms;
    // Prices of stuff
    private final int costcola = 3;
    private final int costzero = 3;
    private final int costlight = 3;
    private final int costfanta = 3;
    private final int costredwine = 8;
    private final int costwhitewine = 7;
    private final int costchips = 3;
    private final int costsoda = 3;
    private final int costwater = 2;


    /*
     Constructor , initialization of room arraylist
     */
    public PassDataToFile(ArrayList<Room> val) {
        rooms = val;
    }








    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public void saveExcelFile() {

        int sumOfCo =0;
        int sumOfLi =0;
        int sumOfSo =0;
        int sumOfFa =0;
        int sumOfZe =0;
        int sumOfWa =0;
        int sumOfRW =0;
        int sumOfWW =0;
        int sumOfCh =0;


        //New Workbook
        Workbook wb = new HSSFWorkbook();

        Cell c = null;

        //Cell style for header row
        CellStyle cs = wb.createCellStyle();

        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        //New Sheet
        Sheet sheet1 = null;
        sheet1 = wb.createSheet("myOrder");
        Row r0 = sheet1.createRow(0);


        c = r0.createCell(0);
        c.setCellValue("DATE: "+ dateFormat.format(date));

        // Generate column headings
        Row row = sheet1.createRow(1);

        c = row.createCell(0);
        c.setCellValue("ROOMS");
        c.setCellStyle(cs);

        c = row.createCell(1);
        c.setCellValue("COLA");
        c.setCellStyle(cs);

        c = row.createCell(2);
        c.setCellValue("FANTA");
        c.setCellStyle(cs);

        c = row.createCell(3);
        c.setCellValue("LIGHT");
        c.setCellStyle(cs);

        c = row.createCell(4);
        c.setCellValue("ZERO");
        c.setCellStyle(cs);

        c = row.createCell(5);
        c.setCellValue("SODA");
        c.setCellStyle(cs);

        c = row.createCell(6);
        c.setCellValue("WATER");
        c.setCellStyle(cs);

        c = row.createCell(7);
        c.setCellValue("W.Wine");
        c.setCellStyle(cs);

        c = row.createCell(8);
        c.setCellValue("R.Wine");
        c.setCellStyle(cs);

        c = row.createCell(9);
        c.setCellValue("Chips");
        c.setCellStyle(cs);

        c = row.createCell(10);
        c.setCellValue("Cost");
        c.setCellStyle(cs);


        sheet1.setColumnWidth(0, (15 * 50));
        sheet1.setColumnWidth(1, (15 * 50));
        sheet1.setColumnWidth(2, (15 * 50));
        sheet1.setColumnWidth(3, (15 * 50));
        sheet1.setColumnWidth(4, (15 * 50));
        sheet1.setColumnWidth(5, (15 * 50));
        sheet1.setColumnWidth(6, (15 * 50));
        sheet1.setColumnWidth(7, (15 * 50));
        sheet1.setColumnWidth(8, (15 * 50));
        sheet1.setColumnWidth(9, (15 * 50));
        sheet1.setColumnWidth(10, (15 * 50));

        int k =2;
        for (int i =0;i< 108;i++){
            // At least one != zero otherwise doesn't save in excel
            if(rooms.get(i).NumOfLight !=0 || rooms.get(i).NumOfCocas !=0 ||rooms.get(i).NumOfFantas !=0 ||rooms.get(i).NumOfZeros !=0 ||
                    rooms.get(i).NumOfWaters !=0 ||rooms.get(i).NumOfSodas !=0 ||rooms.get(i).NumOfChips !=0||rooms.get(i).NumOfRedWines !=0
                    ||rooms.get(i).NumOfWhiteWines !=0) {
                //**************************************************************//
                Row row1 = sheet1.createRow(k);

                c = row1.createCell(0);
                c.setCellValue( rooms.get(i).toString());

                c.setCellStyle(cs);

                c = row1.createCell(1);
                c.setCellValue(rooms.get(i).NumOfCocas);
                c.setCellStyle(cs);

                c = row1.createCell(2);
                c.setCellValue(rooms.get(i).NumOfFantas);
                c.setCellStyle(cs);

                c = row1.createCell(3);
                c.setCellValue(rooms.get(i).NumOfLight);
                c.setCellStyle(cs);

                c = row1.createCell(4);
                c.setCellValue(rooms.get(i).NumOfZeros);
                c.setCellStyle(cs);

                c = row1.createCell(5);
                c.setCellValue(rooms.get(i).NumOfSodas);
                c.setCellStyle(cs);

                c = row1.createCell(6);
                c.setCellValue(rooms.get(i).NumOfWaters);
                c.setCellStyle(cs);

                c = row1.createCell(7);
                c.setCellValue(rooms.get(i).NumOfWhiteWines);
                c.setCellStyle(cs);

                c = row1.createCell(8);
                c.setCellValue(rooms.get(i).NumOfRedWines);
                c.setCellStyle(cs);

                 c = row1.createCell(9);
                c.setCellValue(rooms.get(i).NumOfChips);
                c.setCellStyle(cs);




                sheet1.setColumnWidth(0, (15 * 300));
                sheet1.setColumnWidth(1, (15 * 150));
                sheet1.setColumnWidth(2, (15 * 150));
                sheet1.setColumnWidth(3, (15 * 150));
                sheet1.setColumnWidth(4, (15 * 150));
                sheet1.setColumnWidth(5, (15 * 150));
                sheet1.setColumnWidth(6, (15 * 150));
                sheet1.setColumnWidth(7, (15 * 150));
                sheet1.setColumnWidth(8, (15 * 150));
                sheet1.setColumnWidth(9, (15 * 150));
                k++;
                sumOfCo += rooms.get(i).NumOfCocas;
                sumOfLi += rooms.get(i).NumOfLight;
                sumOfZe += rooms.get(i).NumOfZeros;
                sumOfFa += rooms.get(i).NumOfFantas;
                sumOfWa += rooms.get(i).NumOfWaters;
                sumOfSo += rooms.get(i).NumOfSodas;
                sumOfWW += rooms.get(i).NumOfWhiteWines;
                sumOfRW += rooms.get(i).NumOfRedWines;
                sumOfCh += rooms.get(i).NumOfChips;
                //***************************************************//
            }
        }
        // In the last row show the total of each drink
        Row row2 = sheet1.createRow(k);

        c = row2.createCell(0);
        c.setCellValue("TOTAL");

        c.setCellStyle(cs);

        c = row2.createCell(1);
        c.setCellValue(sumOfCo);
        c.setCellStyle(cs);

        c = row2.createCell(2);
        c.setCellValue(sumOfFa);
        c.setCellStyle(cs);

        c = row2.createCell(3);
        c.setCellValue(sumOfLi);
        c.setCellStyle(cs);

        c = row2.createCell(4);
        c.setCellValue(sumOfZe);
        c.setCellStyle(cs);

        c = row2.createCell(5);
        c.setCellValue(sumOfSo);
        c.setCellStyle(cs);

        c = row2.createCell(6);
        c.setCellValue(sumOfWa);
        c.setCellStyle(cs);

        c = row2.createCell(7);
        c.setCellValue(sumOfWW);
        c.setCellStyle(cs);

        c = row2.createCell(8);
        c.setCellValue(sumOfRW);
        c.setCellStyle(cs);

        c = row2.createCell(9);
        c.setCellValue(sumOfCh);
        c.setCellStyle(cs);

        int money = costchips*sumOfCh + costcola * sumOfCo + costfanta * sumOfFa + costlight * sumOfLi +
                 + costwater * sumOfWa + costzero * sumOfZe + costredwine * sumOfRW + costwhitewine * sumOfWW +
                + costsoda * sumOfSo;
        c = row2.createCell(10);
        c.setCellValue(money);
        c.setCellStyle(cs);


        sheet1.setColumnWidth(0, (15 * 300));
        sheet1.setColumnWidth(1, (15 * 150));
        sheet1.setColumnWidth(2, (15 * 150));
        sheet1.setColumnWidth(3, (15 * 150));
        sheet1.setColumnWidth(4, (15 * 150));
        sheet1.setColumnWidth(5, (15 * 150));
        sheet1.setColumnWidth(6, (15 * 150));
        sheet1.setColumnWidth(7, (15 * 150));
        sheet1.setColumnWidth(8, (15 * 150));
        sheet1.setColumnWidth(9, (15 * 150));
        sheet1.setColumnWidth(10, (15 * 150));

        // Save file

        final File path =
                Environment.getExternalStoragePublicDirectory
                        (
                                //Environment.DIRECTORY_PICTURES
                                Environment.DIRECTORY_DCIM + "/NewFolder/"
                        );

        // Make sure the path directory exists.
        if(!path.exists())
        {
            // Make it, if it doesn't exit
            path.mkdirs();
        }
        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_YEAR);

        String dayOfYearStr = String.valueOf(dayOfMonth);

        final File file = new File(path, "config"+dayOfYearStr+".xls");
        FileOutputStream os = null;
        try {

            os = new FileOutputStream(file);
            wb.write(os);
            Log.w("FileUtils", "Writing file" + file);

        } catch (IOException e) {
            Log.w("FileUtils", "Error writing " + file, e);
        } catch (Exception e) {
            Log.w("FileUtils", "Failed to save file", e);
        } finally {
            try {
                if (null != os)
                    os.close();

            } catch (Exception ex) {
            }
        }

    }

}
