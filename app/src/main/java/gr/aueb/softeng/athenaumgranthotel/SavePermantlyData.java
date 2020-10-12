package gr.aueb.softeng.athenaumgranthotel;

/*
 * This class save data permantly in preferences
 */
import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import java.util.ArrayList;
import static android.content.Context.MODE_PRIVATE;

public class SavePermantlyData {

    public  void saveData(ArrayList<Room> value, Context cn){
        SharedPreferences mPrefs = cn.getSharedPreferences("shared preferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        editor.putString("ROOM", json);
        editor.apply();
    }
}
