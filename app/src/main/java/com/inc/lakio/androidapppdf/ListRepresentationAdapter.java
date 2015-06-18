package com.inc.lakio.androidapppdf;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Show;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Lakio on 18/06/2015.
 */
public class ListRepresentationAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Representation> schedules;

    public ListRepresentationAdapter(Context c, ArrayList<Representation> list) {
        context = c;
        schedules = list;
    }

    @Override
    public int getCount() {
        if (schedules != null) {
            return schedules.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return schedules.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View scheduleView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            scheduleView = inflater.inflate(R.layout.schedule_item_list, null);
        } else {
            scheduleView = convertView;
        }

        TextView item = (TextView) scheduleView.findViewById(R.id.scheduleItemList);

        if (schedules != null) {
            String Horaire = "";
            Calendar c = Calendar.getInstance();
            c.setTime(schedules.get(position).getSchedule());
            Horaire += c.get(Calendar.HOUR_OF_DAY);
            Horaire += ":";
            Horaire += c.get(Calendar.MINUTE);
            if (c.get(Calendar.MINUTE) == 0) {
                Horaire += "0";
            }

            item.setText(Horaire);
        } else {
            item.setText("Impossible de remplir la liste - Réessayer plus tard");

        }


        return scheduleView;
    }
}
