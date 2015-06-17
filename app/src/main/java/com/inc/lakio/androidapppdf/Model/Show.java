package com.inc.lakio.androidapppdf.Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Carn on 15/06/2015.
 */
public class Show extends Entity{
    private long _duration;
    private ArrayList<Representation> _schedules;


    public long getDuration() {
        return _duration;
    }

    public void setDuration(long _duration) {
        this._duration = _duration;
    }

    public ArrayList<Representation> getSchedules() {
        return _schedules;
    }

    public void setSchedules(ArrayList<Representation> _schedules) {
        this._schedules = _schedules;
    }
}
