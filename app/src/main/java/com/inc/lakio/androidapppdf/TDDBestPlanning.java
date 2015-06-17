package com.inc.lakio.androidapppdf;

import com.inc.lakio.androidapppdf.Model.Planning;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Show;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Carn on 16/06/2015.
 */
public class TDDBestPlanning {

    public string createBestPlanning(Long startDate, Long endDate, int nBpause, int pauseRepas)
    {
        Planning result = new Planning();
        ArrayList<Show> shows = new ArrayList<>();
        shows.getAll();
        Long continu = startDate;
        Date d = new Date ();
        d.setTime(startDate);
        result.setStartAt(d);
        d.setTime(endDate);
        result.setEndAt(d);

        for (int i = 1; i < shows.size(); i++)
        {
            ArrayList<Representation> rps = shows.get(i).getSchedules();
            for (int j = 1; j<rps.size();j++)
            {
                Representation tmpRp = rps.get(j);
                if (tmpRp.getSchedule().getTime() >= continu)
                {

                }
                break;
            }

        }


    }
}
