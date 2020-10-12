package gr.aueb.softeng.athenaumgranthotel;

/*
 * In this activity seem all rooms in a floor.You can see also state of each
 * room near the number
 */
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity {
    ArrayList<Room> value;
    MyAdapter  adapter;
    int fst ;
    List<Room> tmp;
    int end;
    SavePermantlyData spd;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floors);
        spd = new SavePermantlyData();
        // Take array of rooms in this floor and the first ,
        // last element of big array from previous activity
        value= (ArrayList<Room>) getIntent().getExtras().getSerializable("Array");
        fst = getIntent().getExtras().getInt("INT1");
        end = getIntent().getExtras().getInt("INT2");
        final ListView listView = (ListView) findViewById(R.id.listview1);
        tmp = value.subList(fst,end);
        adapter = new MyAdapter(this,tmp);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(!value.get(fst+i).isDisturb() ) {
                            Intent i2 = new Intent(Main2Activity.this, Main22Activity.class);
                            i2.putExtra("ROOM", value);
                            i2.putExtra("INT", fst+i);
                            startActivity(i2);
                        }
                    }});
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                                   int pos, long id) {
                            Dialog alert  = onCreateDialog(fst+pos);
                            alert.show();
                            return true;
                    }
                });

        }
    @Override
    public void onResume() {
        super.onResume();
        loadData();
        final ListView listView = (ListView) findViewById(R.id.listview1);
        tmp = value.subList(fst,end);
        adapter = new MyAdapter(this,tmp);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        spd.saveData(value,this);
        Intent i3 = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(i3);
    }

    private void loadData(){
        SharedPreferences mPrefs = getSharedPreferences("shared preferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("ROOM", null);
        Type type = new TypeToken<ArrayList<Room>>(){}.getType();
        value   = gson.fromJson(json, type);

    }







    public Dialog onCreateDialog (final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose state");
        // Each room has 2 states
        // a) The hotel guest has disturb light on
        // b) The hotel guest is in the room
        // Void convert room in initial state
        String[] ch = new String[] {"Disturb","In","Void"};
        builder.setItems(ch, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
                if(which == 0){
                    if(!value.get(pos).isChecked()) {
                        value.get(pos).setDisturb(true);
                        adapter.notifyDataSetChanged();
                    }

                }else if(which==1){
                    if(!value.get(pos).isDisturb()) {
                        value.get(pos).setIn(true);
                        adapter.notifyDataSetChanged();
                    }
                }else if(which ==2){
                    value.get(pos).setChecked(false);
                    value.get(pos).setDisturb(false);
                    value.get(pos).setIn(false);
                    adapter.notifyDataSetChanged();
                }
                spd.saveData(value,getApplicationContext());

            }
        });
        return builder.create();
    }


    public Context getActivity() {
        return this;
    }
}
