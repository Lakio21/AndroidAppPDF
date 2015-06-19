package com.inc.lakio.androidapppdf.Model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Carn on 16/06/2015.
 */
public class Representation implements Serializable{
    private int _idShow;
    private Date _schedule;
    private String _locationTag;
    private String _name;

    public Representation(){}
    public Representation(int id, Date d) {
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

    public void set_locationTag(String _locationTag) {
        this._locationTag = _locationTag;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_locationTag() {
        return _locationTag;
    }

    public String get_name() {
        return _name;
    }

    public boolean isPassed()
    {
        Calendar validDate = new GregorianCalendar();
        validDate.setTime(_schedule);
        return Calendar.getInstance().after(validDate);
    }
}
