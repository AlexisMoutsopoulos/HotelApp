package gr.aueb.softeng.athenaumgranthotel;

/**
 * Created by Alexis on 14/8/2018.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Room> {
    private final Context context;
    private final   List<Room> values;

    public MyAdapter(Context context, List<Room> values) {
        super(context, R.layout.rowlayout,  values );
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values.get(position).toString());
        // Change the icon for Windows and iPhone
        Room s = values.get(position);

        if (s.isDisturb()){

            imageView.setImageResource(R.drawable.dist);
        } else if(s.isChecked()) {
            imageView.setImageResource(R.drawable.ok);
        }else if(s.isIn()){
            imageView.setImageResource(R.drawable.bell);
        }

        return rowView;
    }

}
