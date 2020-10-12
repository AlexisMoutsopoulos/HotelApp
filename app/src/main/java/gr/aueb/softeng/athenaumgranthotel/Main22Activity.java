package gr.aueb.softeng.athenaumgranthotel;

/*
 * This class represent the interior of each room. A room should have 2 cup of tea and 2 glasses on
 * the desk, in the fridge should have 2 water 4 cola 1 fanta 1 soda 2 wine 1 chip.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;

public class Main22Activity extends AppCompatActivity {
    static ArrayList<Room> value;
    private int i;
    SavePermantlyData spd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.room);
        Intent intent = getIntent();
         value = (ArrayList<Room>) intent.getExtras().getSerializable("ROOM");
        i = intent.getExtras().getInt("INT");
        spd = new SavePermantlyData();
        final CheckBox gl1 =(CheckBox) findViewById(R.id.gl1);
        final CheckBox gl2 =(CheckBox) findViewById(R.id.gl2);
        final CheckBox tc1 =(CheckBox) findViewById(R.id.tc1);
        final CheckBox tc2 =(CheckBox) findViewById(R.id.tc2);
        final CheckBox wa1 =(CheckBox) findViewById(R.id.wa1);
        final CheckBox wa2 =(CheckBox) findViewById(R.id.wa2);
        final CheckBox co1 =(CheckBox) findViewById(R.id.co1);
        final CheckBox co2 =(CheckBox) findViewById(R.id.co2);
        final CheckBox fa =(CheckBox) findViewById(R.id.fa);
        final CheckBox so =(CheckBox) findViewById(R.id.so);
        final CheckBox ze =(CheckBox) findViewById(R.id.ze);
        final CheckBox li =(CheckBox) findViewById(R.id.li);
        final CheckBox rw =(CheckBox) findViewById(R.id.rw);
        final CheckBox ww =(CheckBox) findViewById(R.id.ww);
        final CheckBox ch =(CheckBox) findViewById(R.id.ch);
        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setText(""+ value.get(i));

        final CheckBox checkBox =(CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    gl1.setChecked(true);
                    gl2.setChecked(true);
                    tc1.setChecked(true);
                    tc2.setChecked(true);

                }else{
                    gl1.setChecked(false);
                    gl2.setChecked(false);
                    tc1.setChecked(false);
                    tc2.setChecked(false);
                }

            }
        });
        if(value.get(i).isChecked()) {

            gl1.setChecked(value.get(i).glass1);
            gl2.setChecked(value.get(i).glass2);
            co1.setChecked(value.get(i).coca1);
            co2.setChecked(value.get(i).coca2);
            tc1.setChecked(value.get(i).tea1);
            tc2.setChecked(value.get(i).tea2);
            wa1.setChecked(value.get(i).water1);
            wa2.setChecked(value.get(i).water2);
            ze.setChecked(value.get(i).zero);
            li.setChecked(value.get(i).light);
            fa.setChecked(value.get(i).fanta);
            so.setChecked(value.get(i).soda);
            rw.setChecked(value.get(i).redWine);
            ww.setChecked(value.get(i).whiteWine);
            ch.setChecked(value.get(i).chips);



        }
        final Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value.get(i).setChecked(false);
                value.get(i).NumOfWaters=0;
                value.get(i).NumOfLight=0;
                value.get(i).NumOfZeros=0;
                value.get(i).NumOfSodas=0;
                value.get(i).NumOfFantas=0;
                value.get(i).NumOfCocas=0;
                value.get(i).NumOfRedWines=0;
                value.get(i).NumOfWhiteWines=0;
                value.get(i).NumOfChips=0;

              if(!value.get(i).isChecked()) {

                  if (gl1.isChecked()) {
                      value.get(i).glass1 = true;
                  }else{
                      value.get(i).glass1 = false;
                  }
                  if (gl2.isChecked()) {
                      value.get(i).glass2 = true;
                  }else{
                      value.get(i).glass2 = false;
                  }
                  if (tc1.isChecked()) {
                      value.get(i).tea1 = true;
                  }else{
                      value.get(i).tea1 = false;
                  }
                  if (tc2.isChecked()) {
                      value.get(i).tea2 = true;
                  }else{
                      value.get(i).tea2 = false;
                  }
                  if (wa1.isChecked()) {
                      value.get(i).water1 = true;
                      value.get(i).NumOfWaters++;
                  }else{
                      value.get(i).water1 = false;
                  }
                  if (wa2.isChecked()) {
                      value.get(i).water2 = true;
                      value.get(i).NumOfWaters++;
                  }else{
                      value.get(i).water2 = false;
                  }
                  if (co1.isChecked()) {
                      value.get(i).coca1 = true;
                      value.get(i).NumOfCocas++;
                  }else{
                      value.get(i).coca1 = false;
                  }
                  if (co2.isChecked()) {
                      value.get(i).coca2 = true;
                      value.get(i).NumOfCocas++;
                  }else{
                      value.get(i).coca2 = false;
                  }
                  if (li.isChecked()) {
                      value.get(i).light = true;
                      value.get(i).NumOfLight++;
                  }else{
                      value.get(i).light = false;
                  }
                  if (fa.isChecked()) {
                      value.get(i).fanta = true;
                      value.get(i).NumOfFantas++;
                  }else{
                      value.get(i).fanta = false;
                  }
                  if (so.isChecked()) {
                      value.get(i).soda = true;
                      value.get(i).NumOfSodas++;
                  }else{
                      value.get(i).soda = false;
                  }
                  if (ze.isChecked()) {
                      value.get(i).zero = true;
                      value.get(i).NumOfZeros++;
                  }else{
                      value.get(i).zero = false;
                  }if (rw.isChecked()) {
                      value.get(i).redWine = true;
                      value.get(i).NumOfRedWines++;
                  }else{
                      value.get(i).redWine = false;
                  }
                  if (ww.isChecked()) {
                      value.get(i).whiteWine = true;
                      value.get(i).NumOfWhiteWines++;
                  }else{
                      value.get(i).whiteWine = false;
                  }
                  if (ch.isChecked()) {
                      value.get(i).chips = true;
                      value.get(i).NumOfChips++;
                  }else{
                      value.get(i).chips = false;
                  }
                  value.get(i).setChecked(true);
              }
                spd.saveData(value,getApplicationContext());
                finish();
            }
        });


    }

    @Override
    public void onBackPressed(){
        spd.saveData(value,getApplicationContext());
        finish();
    }


}
