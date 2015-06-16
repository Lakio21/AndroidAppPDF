package com.inc.lakio.androidapppdf.Model;

import java.io.Serializable;

/**
 * Created by Lakio on 15/06/2015.
 */
public abstract class Entity implements Serializable
{
    private int _id;
    private String _name;
    private String _description;
    private Double _averageNote;
    private int _totalVote;
    private String _locationTag;
    private Double _latitude;
    private Double _longitude;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public int getTotalVote() {
        return _totalVote;
    }

    public void setTotalVote(int _totalVote) {
        this._totalVote = _totalVote;
    }

    public Double getAverageNote() {
        return _averageNote;
    }

    public void setAverageNote(Double _averageNote) {
        this._averageNote = _averageNote;
    }

    public String getLocationTag() {
        return _locationTag;
    }

    public void setLocationTag(String _locationTag) {
        this._locationTag = _locationTag;
    }

    public Double getLatitude() {
        return _latitude;
    }

    public void setLatitude(Double _latitude) {
        this._latitude = _latitude;
    }

    public Double getLongitude() {
        return _longitude;
    }

    public void setLongitude(Double _longitude) {
        this._longitude = _longitude;
    }
}
