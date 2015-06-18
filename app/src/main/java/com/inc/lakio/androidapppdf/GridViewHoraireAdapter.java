package com.inc.lakio.androidapppdf;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.inc.lakio.androidapppdf.Model.Representation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Lakio on 18/06/2015.
 */
public class GridViewHoraireAdapter extends BaseAdapter {

    private List<Representation> listHoraire = null;
    LayoutInflater layoutInflater;
    Context context;
    private int lastPosition = -1;

    // constructeur
    public GridViewHoraireAdapter(Context context, ArrayList<Representation> listSchedule) {
        this.listHoraire = listSchedule;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listHoraire.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listHoraire.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.schedule_item_list, null);
        } else {
            itemView = convertView;
        }

        if (listHoraire != null) {
            String Horaire = "";
            Calendar c = Calendar.getInstance();
            c.setTime(listHoraire.get(position).getSchedule());
            Horaire += c.get(Calendar.HOUR_OF_DAY);
            Horaire += ":";
            Horaire += c.get(Calendar.MINUTE);
            if (c.get(Calendar.MINUTE) == 0) {
                Horaire += "0";
            }

            TextView item = (TextView) itemView.findViewById(R.id.tvHoraireitem);


            item.setText(Horaire);

            if (listHoraire.get(position).isPassed()) {
                item.setTextColor(Color.RED);
            } else {
                item.setTextColor(Color.GREEN);
            }

        }
        return itemView;
    }
}