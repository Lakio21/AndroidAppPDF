package com.inc.lakio.androidapppdf.Model;

import java.util.Date;

/**
 * Created by Carn on 16/06/2015.
 */
public class Representation {
    private int _idShow;
    private Date _schedule;

    public Representation(int id, Date d)
    {
        _idShow = id;
        _schedule = d;
    }

    public Date getSchedule() {
        return _schedule;
    }

    public void setSchedule(Date _schedule) {
        this._schedule = _schedule;
    }

    public int getIdShow() {
        return _idShow;
    }

    public void setIdShow(int _idShow) {
        this._idShow = _idShow;
    }
}
