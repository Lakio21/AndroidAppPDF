package com.inc.lakio.androidapppdf.Controller;

import android.content.Context;
import android.text.format.Time;

import com.inc.lakio.androidapppdf.Model.Planning;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Show;
import com.inc.lakio.androidapppdf.Model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lakio on 15/06/2015.
 */
public class PlanningController {
    private JsonParser _jsonParser;
    private XmlStream _xmlStream;

    public Boolean saveCustomPlanning(Planning _planning,String _fileName,Context c) {
        try {
            _jsonParser = new JsonParser();
            String tmp = _jsonParser.parsePlanningToJson(_planning);
            _xmlStream = new XmlStream();
            if (_xmlStream.setPlanningInfo(tmp,_fileName,c)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Planning getSavedPlanning(String _fileName, Context c) {
        _xmlStream = new XmlStream();
        String tmp = _xmlStream.getPlanningInfo(_fileName, c);
        _jsonParser = new JsonParser();
        return _jsonParser.parseToPlanning(tmp);

    }

    public Planning getBestPlanning(Date start, Date end, int nBPause, int tempsRepas) {

        _jsonParser = new JsonParser();
        String s = null;
        try {
            s = RequestManager.getInstance().post("/planning/", _jsonParser.parsePlanningInfoToJson(start, end, nBPause, tempsRepas));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return _jsonParser.parseToPlanning(s);
    }

    public boolean isPassed(Date schedule) {

        Calendar now = Calendar.getInstance();
        Calendar showTime = Calendar.getInstance();
        showTime.setTime(schedule);

        switch (now.compareTo(showTime)) {
            case -1:
                return false;

            case 1:
                return true;

            case 0:
                return true;

            default:
                return true;
        }
    }

}
