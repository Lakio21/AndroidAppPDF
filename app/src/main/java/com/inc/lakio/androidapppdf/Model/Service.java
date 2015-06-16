package com.inc.lakio.androidapppdf.Model;

import java.util.Date;

/**
 * Created by Carn on 15/06/2015.
 */
public class Service extends Entity
{
    private Date _openAt;
    private Date _closeAt;

    public Date getOpenAt() {
        return _openAt;
    }

    public void setOpenAt(Date _openAt) {
        this._openAt = _openAt;
    }

    public Date getCloseAt() {
        return _closeAt;
    }

    public void setCloseAt(Date _closeAt) {
        this._closeAt = _closeAt;
    }
}
