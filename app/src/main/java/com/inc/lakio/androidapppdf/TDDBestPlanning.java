package com.inc.lakio.androidapppdf;

import com.inc.lakio.androidapppdf.Model.Planning;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Show;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Carn on 16/06/2015.
 */
public class TDDBestPlanning {

    public String createBestPlanning(Long startDate, Long endDate, int nBpause, int pauseRepas, String loginUser)
    {
        //region déclaration
        Planning result = new Planning();
        ArrayList<Show> shows = new ArrayList<>();
        Date d = new Date ();
        //endregion

        //region initialisation
        Long continu = startDate;
        Long end = endDate;
        boolean processEnd = false;
        Representation midi = new Representation();
        Representation soir = new Representation();
        Date dt = new Date();
        dt.setTime(39600);
        midi.setSchedule(dt);
        midi = transformeDate(midi);
        dt.setTime(69300);
        soir.setSchedule(dt);
        soir = transformeDate(soir);
        //endregion
        //shows.getAll();

        //region init planning
        d.setTime(startDate);
        result.setStartAt(d);
        d.setTime(endDate);
        result.setEndAt(d);
        result.setLoginUser(loginUser);
        //endregion


        boolean firstStep = true;
        double lastLatitude = 0;
        double lastLongitude = 0;
        boolean repasMidi = false;
        boolean repasSoir = false;
        int cmpShow = 0;
        while (!processEnd)
        {
            int showChose = 0;
            int representationChose = 0;
            long tmpTime = 2000000000;
            boolean timeChange = false;
            long moveTime = 0;

            for (int i = 0; i < shows.size(); i++)
            {
                ArrayList<Representation> rps = shows.get(i).getSchedules();

                if (!firstStep)
                {
                    moveTime = pointToPointDistance(lastLatitude,shows.get(i).getLatitude(),lastLongitude,shows.get(i).getLongitude());
                    moveTime = moveTime*60;
                }

                for (int j = 0; j < rps.size(); j++)
                {
                    Representation Rp = rps.get(j);
                    Rp = transformeDate(Rp);
                    long ecart = Rp.getSchedule().getTime() - (continu+moveTime);

                    if (ecart > 0 && ecart < tmpTime)
                    {
                        tmpTime = ecart;
                        timeChange = true;
                        showChose = i;
                        representationChose = j;
                    }
                }
            }
            result.setOneRepresentationList(shows.get(showChose).getSchedules().get(representationChose));

            if (timeChange = true)
            {
                lastLatitude = shows.get(showChose).getLatitude();
                lastLongitude = shows.get(showChose).getLongitude();
            }
            continu = continu + tmpTime;
            continu = continu + moveTime;
            continu = continu + shows.get(showChose).getDuration()*60;

            if (!repasMidi && midi.getSchedule().getTime() <= continu)
            {
                continu = continu + 3600;
                repasMidi = true;
            }
            if (!repasSoir && soir.getSchedule().getTime() <= continu)
            {
                continu = continu + 3600;
                repasSoir = true;
            }
            if (continu >= endDate)
            {
                processEnd = true;
            }
            cmpShow += 1;
            if (cmpShow >= 2) // pause de 15 minutes
            {
                continu = continu + 900;
                cmpShow = 0;
            }
            shows.remove(showChose);
        }
        return null;
    }

    private Representation transformeDate(Representation rp)
    {
        Calendar c = Calendar.getInstance();
        Calendar x = Calendar.getInstance();
        c.setTime(rp.getSchedule());
        int heure = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);
        x.set(Calendar.HOUR_OF_DAY,heure);
        x.set(Calendar.MINUTE,min);
        x.set(Calendar.SECOND,00);
        rp.setSchedule(x.getTime());
        return rp;
    }

    private int pointToPointDistance (double x1, double y1, double x2, double y2)
    {
        double w1 = x2 - x1;
        double w2 = y2 - y1;
        double w3 = Math.pow(w1,2)+Math.pow(w2,2);
        return (int)Math.sqrt(w3);
    }
}
