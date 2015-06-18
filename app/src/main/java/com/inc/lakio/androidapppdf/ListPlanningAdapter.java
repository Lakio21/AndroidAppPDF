package com.inc.lakio.androidapppdf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.inc.lakio.androidapppdf.Model.Show;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Lakio on 18/06/2015.
 */
public class ListPlanningAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Show> shows;

    public ListPlanningAdapter(Context c, ArrayList<Show> list)
    {
        context = c;
        shows = list;
    }

    @Override
    public int getCount() {
        if(shows != null) {
            return shows.size();
        }else{
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return shows.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TwoLineListItem twoLineListItem;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            twoLineListItem = (TwoLineListItem) inflater.inflate(android.R.layout.simple_list_item_2, null);
        } else {
            twoLineListItem = (TwoLineListItem) convertView;
        }

        TextView text1 = twoLineListItem.getText1();
        TextView text2 = twoLineListItem.getText2();

        if (shows != null)
        {
            String Horaire = "";

                Calendar c = Calendar.getInstance();
                c.setTime(shows.get(position).getSelectedSchedule().getSchedule());
                Horaire += c.get(Calendar.HOUR_OF_DAY);
                Horaire += ":";
                Horaire += c.get(Calendar.MINUTE);

                if (c.get(Calendar.MINUTE) == 0)
                {
                    Horaire += "0";
                }



            text1.setText(shows.get(position).getName() + " - Emplacement " + shows.get(position).getLocationTag());
            text2.setText(Horaire);
        }
        else
        {
            text1.setText("Impossible de remplir la liste");
            text2.setText("Réessayer plus tard");
        }


        return twoLineListItem;
    }
}
