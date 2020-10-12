package gr.aueb.softeng.athenaumgranthotel;
/*
 * This is the first screen of app. There is a list of floors (1-7).
 */
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Initialize init;
    SavePermantlyData spd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btn = (Button) findViewById(R.id.button2);
        init = new Initialize();
        spd = new SavePermantlyData();
        // Clear data button
        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Clearing data...!", 3000).show();
                init = new Initialize();
                spd.saveData(init.all,getApplicationContext());
            }
        });


        String[] arr = new String[]{"Floor 1", "Floor 2", "Floor 3", "Floor 4", "Floor 5", "Floor 6", "Floor 7"};

        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(adapter);
        // Click each item in arraylist and you access in the corresponding floor
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                init = new Initialize();
                // check if data is saved in preferences
                checkIfexistData();
                if (i == 0) {


                    Intent i2 = new Intent(MainActivity.this, Main2Activity.class);
                    i2.putExtra("Array", init.all);
                    i2.putExtra("INT1", 0);
                    i2.putExtra("INT2", 23);
                    startActivity(i2);

                } else if (i == 1) {
                    Intent i2 = new Intent(MainActivity.this, Main2Activity.class);
                    i2.putExtra("Array", init.all);
                    i2.putExtra("INT1", 23);
                    i2.putExtra("INT2", 39);
                    startActivity(i2);
                } else if (i == 2) {
                    Intent i2 = new Intent(MainActivity.this, Main2Activity.class);
                    i2.putExtra("Array", init.all);
                    i2.putExtra("INT1", 39);
                    i2.putExtra("INT2", 55);
                    startActivity(i2);
                } else if (i == 3) {
                    Intent i2 = new Intent(MainActivity.this, Main2Activity.class);
                    i2.putExtra("Array", init.all);
                    i2.putExtra("INT1", 55);
                    i2.putExtra("INT2", 71);
                    startActivity(i2);
                } else if (i == 4) {
                    Intent i2 = new Intent(MainActivity.this, Main2Activity.class);
                    i2.putExtra("Array", init.all);
                    i2.putExtra("INT1", 71);
                    i2.putExtra("INT2", 87);
                    startActivity(i2);
                } else if (i == 5) {
                    Intent i2 = new Intent(MainActivity.this, Main2Activity.class);
                    i2.putExtra("Array", init.all);
                    i2.putExtra("INT1", 87);
                    i2.putExtra("INT2", 103);
                    startActivity(i2);
                } else if (i == 6) {
                    Intent i2 = new Intent(MainActivity.this, Main2Activity.class);
                    i2.putExtra("Array", init.all);
                    i2.putExtra("INT1", 103);
                    i2.putExtra("INT2", 108);
                    startActivity(i2);
                }

            }
        });

        // Long press in items and choose an option from dialoge
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {


                Dialog alertDialog = onCreateDialog(pos);
                alertDialog.show();
                return true;
            }
        });

    }


    /*
        Check data if exist in preferences
     */

    private void checkIfexistData(){
        SharedPreferences mPrefs = getSharedPreferences("shared preferences", MODE_PRIVATE);
        String value = mPrefs.getString("ROOM",null);
        if (value == null) {
            spd.saveData(init.all,this);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        loadData();


    }

    public void onBackPressed() {
        super.onBackPressed();

        spd.saveData(init.all,this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            this.finishAffinity();
        }
    }
    /*
        Retrieve data from preferences
     */
    private void loadData() {
        SharedPreferences mPrefs = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("ROOM", null);
        Type type = new TypeToken<ArrayList<Room>>() {
        }.getType();
        init.all = gson.fromJson(json, type);
    }

    /*
     This function creates the three punkt in the top right of screen
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.add(1, 1, 1, "Show shortages of plates");
        menu.add(1, 2, 1, "Export Data");
        menu.add(1, 3, 1, "Show all Disturbs Rooms");
        menu.add(1, 4, 1, "Show all IN Rooms");
        menu.add(1,5,1,"Create Chart");
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == 1) {
            ArrayList<String> shortages = new ArrayList<>();
            //Creates an arraylist with shortages
            for (Room rm : init.all) {
                //Check only the rooms which i was in
                if (rm.isChecked() == true) {
                    if (rm.glass1 == false && rm.glass2 == false) {

                        shortages.add(rm + " 2 glasses");
                    } else if (rm.glass1 == false || rm.glass2 == false) {
                        shortages.add(rm + " 1 glass");
                    }

                    if (rm.tea1 == false && rm.tea2 == false) {

                        shortages.add(rm + " 2 tea cups");
                    } else if (rm.tea1 == false || rm.tea2 == false) {
                        shortages.add(rm + " 1 tea cup");
                    }
                }
            }
            final String[] shorta = shortages.toArray(new String[shortages.size()]);
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle("Shortages");
            dialogBuilder.setItems(shorta, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    String selectedText = shorta[item].toString();  //Selected item in listview
                }
            });
            //Create alert dialog object via builder
            AlertDialog alertDialogObject = dialogBuilder.create();
            //Show the dialog
            alertDialogObject.show();
        } else if (item.getItemId() == 2) {

            PassDataToFile ps = new PassDataToFile(init.all);

            ps.saveExcelFile();
            Toast.makeText(this,"xls file was created",Toast.LENGTH_SHORT).show();


        } else if (item.getItemId() == 3) {
            Dialog dialog1 = new Dialog(getActivity());
            dialog1.setContentView(R.layout.content_main);
            ListView lv = (ListView) dialog1.findViewById(R.id.listview);
            dialog1.setCancelable(true);


            dialog1.setTitle("Disturb Rooms");


            ArrayList<String> array = new ArrayList<>();
            for (int i = 0; i < init.all.size(); i++) {
                if (init.all.get(i).isDisturb()) {
                    array.add(init.all.get(i).getNumber() + ": Disturb");
                }

            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array);
            lv.setAdapter(adapter);
            dialog1.show();
        } else if(item.getItemId()==4){
            Dialog dialog1 = new Dialog(getActivity());
            dialog1.setContentView(R.layout.content_main);
            ListView lv = (ListView) dialog1.findViewById(R.id.listview);
            dialog1.setCancelable(true);


            dialog1.setTitle("IN Rooms");


            ArrayList<String> array = new ArrayList<>();
            for (int i = 0; i < init.all.size(); i++) {
                if (init.all.get(i).isIn()) {
                    array.add(init.all.get(i).getNumber() + ": Client was in");
                }

            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array);
            lv.setAdapter(adapter);
            dialog1.show();
        }else if(item.getItemId()==5){
            Intent i = new Intent(MainActivity.this,Chart.class);
        startActivity(i);
        }
            return super.onOptionsItemSelected(item);
        }





    public Dialog onCreateDialog (final int pos) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String[] ch = new String[] {"Show Disturb Rooms","Show In Rooms","Show the number of drinks" };
        builder.setItems(ch, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
                Dialog dialog1 = new Dialog(getActivity());
                dialog1.setContentView(R.layout.content_main);
                ListView lv = (ListView ) dialog1.findViewById(R.id.listview);
                dialog1.setCancelable(true);
                if(which == 0){

                    dialog1.setTitle("Disturb Rooms");





                    if(pos==0) {
                        ArrayList<String> array = new ArrayList<>();
                        for (int i = 0; i <23;i++){
                            if(init.all.get(i).isDisturb()){
                                array.add(init.all.get(i).getNumber() + ": Disturb");
                            }

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                        lv.setAdapter(adapter);
                    }else if(pos==1){
                        ArrayList<String> array = new ArrayList<>();
                        for (int i = 23; i < 39; i++) {
                            if (init.all.get(i).isDisturb()) {
                                array.add(init.all.get(i).getNumber() + ": Disturb");
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                            lv.setAdapter(adapter);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                        lv.setAdapter(adapter);
                    }else if(pos==2) {
                        ArrayList<String> array = new ArrayList<>();
                        for (int i = 39; i < 55; i++) {
                            if (init.all.get(i).isDisturb()) {
                                array.add(init.all.get(i).getNumber() + ": Disturb");
                            }

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                        lv.setAdapter(adapter);
                    }else if(pos==3){
                        ArrayList<String> array = new ArrayList<>();
                        for (int i = 55; i < 71; i++) {
                            if (init.all.get(i).isDisturb()) {
                                array.add(init.all.get(i).getNumber() + ": Disturb");
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                            lv.setAdapter(adapter);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                        lv.setAdapter(adapter);
                    }else if(pos==4){
                        ArrayList<String> array = new ArrayList<>();
                        for (int i = 71; i < 87; i++) {
                            if (init.all.get(i).isDisturb()) {
                                array.add(init.all.get(i).getNumber() + ": Disturb");
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                            lv.setAdapter(adapter);
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                        lv.setAdapter(adapter);
                    }else if(pos==5){
                        ArrayList<String> array = new ArrayList<>();
                        for (int i = 87; i < 103; i++) {
                            if (init.all.get(i).isDisturb()) {
                                array.add(init.all.get(i).getNumber() + ": Disturb");
                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                        lv.setAdapter(adapter);
                    }else if(pos==6){
                        ArrayList<String> array = new ArrayList<>();
                        for (int i = 103; i < 108; i++) {
                            if (init.all.get(i).isDisturb()) {
                                array.add(init.all.get(i).getNumber() + ": Disturb");
                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                        lv.setAdapter(adapter);
                    }

                    dialog1.show();

                    }else if(which==1){
                         dialog1.setTitle("In Rooms");
                         ArrayList<String> array = new ArrayList<>();

                   // GIA OLOYS TOYS OROFOUS


                          if(pos==0) {

                              for (int i = 0; i < 23; i++) {
                                  if (init.all.get(i).isIn()) {
                                      array.add(init.all.get(i).getNumber() + ": Client was in");
                                  }
                              }

                          }else if(pos==1){
                              for (int i = 23; i < 39; i++) {
                                  if (init.all.get(i).isIn()) {
                                      array.add(init.all.get(i).getNumber() + ": Client was in");
                                  }
                              }
                          }else if(pos==2) {
                              for (int i = 39; i < 55; i++) {
                                  if (init.all.get(i).isIn()) {
                                      array.add(init.all.get(i).getNumber() + ": Client was in");
                                  }
                              }
                          }else if(pos==3){
                              for (int i = 55; i < 71; i++) {
                                  if (init.all.get(i).isIn()) {
                                      array.add(init.all.get(i).getNumber() + ": Client was in");
                                  }
                              }
                          }else if(pos==4){
                              for (int i = 71; i < 87; i++) {
                                  if (init.all.get(i).isIn()) {
                                      array.add(init.all.get(i).getNumber() + ": Client was in");
                                  }
                              }
                          }else if(pos==5){
                              for (int i = 87; i < 103; i++) {
                                  if (init.all.get(i).isIn()) {
                                      array.add(init.all.get(i).getNumber() + ": Client was in");
                                  }
                              }
                          }else if(pos==6){
                              for (int i = 103; i < 108; i++) {
                                  if (init.all.get(i).isIn()) {
                                      array.add(init.all.get(i).getNumber() + ": Client was in");
                                  }
                              }
                          }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array);
                    lv.setAdapter(adapter);


                }else if(which == 2){
                        dialog1.setTitle("Number of drinks");
                    ArrayList<String> array = new ArrayList<>();
                    int wa=0,co=0,so=0,fa=0,li=0,ze=0,rw=0,ww=0,ch=0;
                    if(pos==0) {
                         wa=0;co=0;so=0;fa=0;li=0;ze=0;
                        int be = 0; int fi=23;
                        for (int i = be; i < fi; i++) {
                            wa += init.all.get(i).NumOfWaters;
                        }
                        for (int i = be; i < fi; i++) {
                            co += init.all.get(i).NumOfCocas;
                        }
                        for (int i = be; i < fi; i++) {
                            fa += init.all.get(i).NumOfFantas;
                        }
                        for (int i = be; i < fi; i++) {
                            li += init.all.get(i).NumOfLight;
                        }
                        for (int i = be; i < fi; i++) {
                            ze += init.all.get(i).NumOfZeros;
                        }
                        for (int i = be; i < fi; i++) {
                            so += init.all.get(i).NumOfSodas;
                        }
                        for (int i = be; i < fi; i++) {
                            rw += init.all.get(i).NumOfRedWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ww += init.all.get(i).NumOfWhiteWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ch += init.all.get(i).NumOfChips;
                        }
                    }else if(pos == 2){
                        wa=0;co=0;so=0;fa=0;li=0;ze=0;rw=0;ww=0;ch=0;
                        int be = 39; int fi=55;
                        for (int i = be; i < fi; i++) {
                            wa += init.all.get(i).NumOfWaters;
                        }
                        for (int i = be; i < fi; i++) {
                            co += init.all.get(i).NumOfCocas;
                        }
                        for (int i = be; i < fi; i++) {
                            fa += init.all.get(i).NumOfFantas;
                        }
                        for (int i = be; i < fi; i++) {
                            li += init.all.get(i).NumOfLight;
                        }
                        for (int i = be; i < fi; i++) {
                            ze += init.all.get(i).NumOfZeros;
                        }
                        for (int i = be; i < fi; i++) {
                            so += init.all.get(i).NumOfSodas;
                        }
                        for (int i = be; i < fi; i++) {
                            rw += init.all.get(i).NumOfRedWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ww += init.all.get(i).NumOfWhiteWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ch += init.all.get(i).NumOfChips;
                        }
                    }else if(pos == 3){
                        wa=0;co=0;so=0;fa=0;li=0;ze=0;rw=0;ww=0;ch=0;
                        int be = 55; int fi= 71;
                        for (int i = be; i < fi; i++) {
                            wa += init.all.get(i).NumOfWaters;
                        }
                        for (int i = be; i < fi; i++) {
                            co += init.all.get(i).NumOfCocas;
                        }
                        for (int i = be; i < fi; i++) {
                            fa += init.all.get(i).NumOfFantas;
                        }
                        for (int i = be; i < fi; i++) {
                            li += init.all.get(i).NumOfLight;
                        }
                        for (int i = be; i < fi; i++) {
                            ze += init.all.get(i).NumOfZeros;
                        }
                        for (int i = be; i < fi; i++) {
                            so += init.all.get(i).NumOfSodas;
                        }
                        for (int i = be; i < fi; i++) {
                            rw += init.all.get(i).NumOfRedWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ww += init.all.get(i).NumOfWhiteWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ch += init.all.get(i).NumOfChips;
                        }
                    }else if(pos == 4){
                        wa=0;co=0;so=0;fa=0;li=0;ze=0;rw=0;ww=0;ch=0;
                        int be = 71; int fi=86;
                        for (int i = be; i < fi; i++) {
                            wa += init.all.get(i).NumOfWaters;
                        }
                        for (int i = be; i < fi; i++) {
                            co += init.all.get(i).NumOfCocas;
                        }
                        for (int i = be; i < fi; i++) {
                            fa += init.all.get(i).NumOfFantas;
                        }
                        for (int i = be; i < fi; i++) {
                            li += init.all.get(i).NumOfLight;
                        }
                        for (int i = be; i < fi; i++) {
                            ze += init.all.get(i).NumOfZeros;
                        }
                        for (int i = be; i < fi; i++) {
                            so += init.all.get(i).NumOfSodas;
                        }
                        for (int i = be; i < fi; i++) {
                            rw += init.all.get(i).NumOfRedWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ww += init.all.get(i).NumOfWhiteWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ch += init.all.get(i).NumOfChips;
                        }
                    }else if(pos == 5){
                        wa=0;co=0;so=0;fa=0;li=0;ze=0;rw=0;ww=0;ch=0;
                        int be = 86; int fi=103;
                        for (int i = be; i < fi; i++) {
                            wa += init.all.get(i).NumOfWaters;
                        }
                        for (int i = be; i < fi; i++) {
                            co += init.all.get(i).NumOfCocas;
                        }
                        for (int i = be; i < fi; i++) {
                            fa += init.all.get(i).NumOfFantas;
                        }
                        for (int i = be; i < fi; i++) {
                            li += init.all.get(i).NumOfLight;
                        }
                        for (int i = be; i < fi; i++) {
                            ze += init.all.get(i).NumOfZeros;
                        }
                        for (int i = be; i < fi; i++) {
                            so += init.all.get(i).NumOfSodas;
                        }
                        for (int i = be; i < fi; i++) {
                            rw += init.all.get(i).NumOfRedWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ww += init.all.get(i).NumOfWhiteWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ch += init.all.get(i).NumOfChips;
                        }
                    }else if(pos == 6){
                        wa=0;co=0;so=0;fa=0;li=0;ze=0;rw=0;ww=0;ch=0;
                        int be = 103; int fi= 108;
                        for (int i = be; i < fi; i++) {
                            wa += init.all.get(i).NumOfWaters;
                        }
                        for (int i = be; i < fi; i++) {
                            co += init.all.get(i).NumOfCocas;
                        }
                        for (int i = be; i < fi; i++) {
                            fa += init.all.get(i).NumOfFantas;
                        }
                        for (int i = be; i < fi; i++) {
                            li += init.all.get(i).NumOfLight;
                        }
                        for (int i = be; i < fi; i++) {
                            ze += init.all.get(i).NumOfZeros;
                        }
                        for (int i = be; i < fi; i++) {
                            so += init.all.get(i).NumOfSodas;
                        }
                        for (int i = be; i < fi; i++) {
                            rw += init.all.get(i).NumOfRedWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ww += init.all.get(i).NumOfWhiteWines;
                        }
                        for (int i = be; i < fi; i++) {
                            ch += init.all.get(i).NumOfChips;
                        }
                    }
                    array.add("Water: " + wa + " bottles");
                    array.add("Cola: " + co + " cans");
                    array.add("Light: " + li + " cans");
                    array.add("Soda: " + so + " cans");
                    array.add("Zero: " + ze + " cans");
                    array.add("Fanta: " + fa + " cans");
                    array.add("Red Wines: " + rw + " bottles");
                    array.add("White Wines: " + ww + " bottles");
                    array.add("Chips: " + ch + " bags");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,array);
                    lv.setAdapter(adapter);
                }
                    dialog1.show();
            }
        });
        return builder.create();
    }

    public Context getActivity() {
        return this;
    }
}
