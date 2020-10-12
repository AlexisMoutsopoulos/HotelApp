package gr.aueb.softeng.athenaumgranthotel;

/*
 * This class create a pie for daily consumption of drinks
 */
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Chart extends AppCompatActivity {
PieChart sChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        sChart = (PieChart) findViewById(R.id.piechart);
        sChart.setRotationEnabled(true);
        sChart.setHoleRadius(25f);
        sChart.setDrawEntryLabels(true);
        sChart.setTransparentCircleAlpha(0);

        try {
            addDataForPieChartToday( );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        Read data from saved file
     */
    private void addDataForPieChartToday() throws IOException {
        //find the file from storage
        final File path =
                Environment.getExternalStoragePublicDirectory
                        (
                                //Environment.DIRECTORY_PICTURES
                                Environment.DIRECTORY_DCIM + "/NewFolder/"
                        );


        Calendar cal = Calendar.getInstance();
        int dayOfMonth = cal.get(Calendar.DAY_OF_YEAR);

        String dayOfYearStr = String.valueOf(dayOfMonth);
        // read data from file
        ReadFromExcel rd = new ReadFromExcel();
        // each drink is read seperately
        int colas = rd.returnCola(path +  "/config"+dayOfYearStr+".xls");
        int zeros = rd.returnZero(path +  "/config"+dayOfYearStr+".xls");
        int sodas = rd.returnSoda(path +  "/config"+dayOfYearStr+".xls");
        int light = rd.returnLight(path +  "/config"+dayOfYearStr+".xls");
        int fantas = rd.returnFanta(path +  "/config"+dayOfYearStr+".xls") ;
        int waters =  rd.returnWaters(path +  "/config"+dayOfYearStr+".xls");
        int redwines =  rd.returnRWines(path +  "/config"+dayOfYearStr+".xls");
        int whitewines =  rd.returnWWines(path +  "/config"+dayOfYearStr+".xls");
        int chips =  rd.returnChips(path +  "/config"+dayOfYearStr+".xls");

        int sumOfDrinks =  colas + zeros +sodas + light +fantas +  waters;
        Description dsc = new Description();
        dsc.setText("Total: "+ sumOfDrinks);
        sChart.setDescription(dsc);
        float percola = (float)colas/sumOfDrinks*100 ;
        float perczero = (float)zeros/sumOfDrinks*100;
        float percfanta =(float)fantas/sumOfDrinks*100;
        float perclight = (float)light/sumOfDrinks*100;
        float percsoda = (float)sodas/sumOfDrinks*100;
        float percWater = (float)waters/sumOfDrinks*100;
        float percWWine = (float)whitewines/sumOfDrinks*100;
        float percRWine = (float)redwines/sumOfDrinks*100;
        float percChips = (float)chips/sumOfDrinks*100;
        float[] yData = {percola,perczero,percfanta,perclight,percsoda ,percWater,percWWine,percRWine,percChips};
        String[] xValues = {"Cola: "+colas,"Zero: "+ zeros,"Fanta: "+ fantas,"Light: "+light,"Soda: "+sodas,"Water: "+waters,"W.Wines: " +whitewines,"R.Wines: "+ redwines,"Chips: "+ chips};

        List<PieEntry> yEntries = new ArrayList<>();


        for(int i = 0; i< yData.length;i++){
            yEntries.add(new PieEntry(yData[i] ,xValues[i]));
        }



        PieDataSet pieDataSet = new PieDataSet(yEntries ,"Soft Drinks");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.MAGENTA);
        colors.add(Color.GREEN);
        colors.add(Color.GRAY);
        colors.add(Color.DKGRAY);
        colors.add(Color.CYAN);
        pieDataSet.setColors(colors);

        Legend legend = sChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);

        PieData pieData = new PieData(pieDataSet);
        sChart.setData(pieData);
        sChart.invalidate();
    }
}
