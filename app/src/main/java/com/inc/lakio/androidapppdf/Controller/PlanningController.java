package com.inc.lakio.androidapppdf.Controller;

import com.inc.lakio.androidapppdf.Model.Planning;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.User;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Lakio on 15/06/2015.
 */
public class PlanningController
{
    private JsonParser _jsonParser;
    private XmlStream _xmlStream;
    private RequestManager _requestManger;

    public String saveCustomPlanning(ArrayList<Representation> shows)
    {
        String result = "";
        try
        {
            Planning planning = new Planning();
            planning.setRepresentationList(shows);
            planning.setStartAt(shows.get(1).getSchedule());
            planning.setEndAt(shows.get(shows.size() - 1)
                    .getSchedule());
            planning.setLoginUser(User.getInstance().getLogin());
            _jsonParser = new JsonParser();
            String tmp = _jsonParser.parsePlanningToJson(planning);
            _xmlStream = new XmlStream();
            if (_xmlStream.setPlanningInfo(tmp))
            {
                result = tmp;
                return result;
            }
            else
            {
                return result;
            }
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public Planning getSavedPlanning()
    {
        _xmlStream = new XmlStream();
        String tmp = _xmlStream.getPlanningInfo();
        _jsonParser = new JsonParser();
        return  _jsonParser.parseToPlanning(tmp);

    }

    public Planning getBestPlanning(Date start,Date end, int nBPause, int tempsRepas)
    {
        _requestManger = new RequestManager();
        _jsonParser = new JsonParser();

        return null;
    }
}
