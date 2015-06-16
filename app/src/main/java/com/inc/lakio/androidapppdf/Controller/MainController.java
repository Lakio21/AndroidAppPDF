package com.inc.lakio.androidapppdf.Controller;

import com.inc.lakio.androidapppdf.Model.Entity;
import com.inc.lakio.androidapppdf.Model.Planning;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Restaurant;
import com.inc.lakio.androidapppdf.Model.Shop;
import com.inc.lakio.androidapppdf.Model.Show;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lakio on 15/06/2015.
 */
public class MainController {

    RequestManager _requestManager;
    JsonParser _jsonParser;
    PlanningController _planningControler;

    ArrayList<Show> showsArrayList;

    public MainController()
    {
        _requestManager = new RequestManager();
        _jsonParser = new JsonParser();
    }

    public ArrayList<Show> getGlobalPlanning()
    {
        showsArrayList = new ArrayList<Show>();

        String result = _requestManager.get("/shows");

        showsArrayList = _jsonParser.parseToShows(result);

        return showsArrayList;
    }

    public Planning getCustomPlanning ()
    {
        _planningControler = new PlanningController();
        Planning p = _planningControler.getSavedPlanning();

        return p;
    }

    public Boolean setCustomPlanning(ArrayList<Representation> representations)
    {
        _planningControler = new PlanningController();
        String result = _planningControler.saveCustomPlanning(representations);
        if (result.equals("")) {
            return false;
        }
        else
        {
            _requestManager = new RequestManager();
            _requestManager.post("/planning/save",result);
            return true;
        }

    }

    public void vote(Entity entity, int vote)
    {
        String type = "";

        if (entity instanceof Show)
        {
            type = "show";
        }
        else if(entity instanceof Restaurant)
        {
            type = "restaurant";
        }
        else if (entity instanceof Shop)
        {
            type = "shop";
        }

        _requestManager.post("/vote", _jsonParser.parseVoteToJson(entity.getId(), type, vote));
    }

    public Planning generateBestPlanning(Date start,Date end, int nBPause, int tempsRepas)
    {
        _planningControler = new PlanningController();
        return  _planningControler.getBestPlanning(start,end,nBPause,tempsRepas);
    }

}
