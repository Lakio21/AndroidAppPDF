package com.inc.lakio.androidapppdf.Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Carn on 15/06/2015.
 */
public class Planning {
    private int _id;
    private Date _startAt;
    private Date _endAt;
    private ArrayList<Representation> _representationList;
    private String _loginUser;

    public Planning()
    {
        _representationList = new ArrayList<>();
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public Date getStartAt() {
        return _startAt;
    }

    public void setStartAt(Date _startAt) {
        this._startAt = _startAt;
    }

    public Date getEndAt() {
        return _endAt;
    }

    public void setEndAt(Date _endAt) {
        this._endAt = _endAt;
    }

    public ArrayList<Representation> getRepresentationList() {
        return _representationList;
    }

    public void setRepresentationList(ArrayList<Representation> _showList) {
        this._representationList = _showList;
    }

    public void setOneRepresentationList(Representation _show) {
        this._representationList.add(_show);
    }

    public String getLoginUser() {
        return _loginUser;
    }

    public void setLoginUser(String _loginUser) {
        this._loginUser = _loginUser;
    }
}
