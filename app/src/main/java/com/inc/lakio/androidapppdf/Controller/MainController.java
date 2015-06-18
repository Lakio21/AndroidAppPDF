package com.inc.lakio.androidapppdf.Controller;

import com.inc.lakio.androidapppdf.Model.Entity;
import com.inc.lakio.androidapppdf.Model.Planning;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Restaurant;
import com.inc.lakio.androidapppdf.Model.Shop;
import com.inc.lakio.androidapppdf.Model.Show;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Lakio on 15/06/2015.
 */
public class MainController {

    JsonParser _jsonParser;
    PlanningController _planningControler;

    public MainController()
    {
        _jsonParser = new JsonParser();
    }

    public ArrayList<Show> getGlobalPlanning(){

        ArrayList<Show> showsArrayList = new ArrayList<>();

        String result = null;
        try {
            result = RequestManager.getInstance().get("shows");
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        //result = "[{\"id\": 1,\"name\": \"Le signe du triomphe\",\"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\",\"averageNote\": 0,\"totalVote\": 0,\"duration\": 45,\"location\":{\"id\": 1,\"tag\": 1,\"latitude\": 175.26,\"longitude\": 52.1},\"schedulesList\":[{\"id\": 1,\"showId\": 1,\"time\": 29700},{\"id\": 2,\"showId\": 1,\"time\": 37800},{\"id\": 3,\"showId\": 1,\"time\": 55800}]}]";
        showsArrayList = _jsonParser.parseToShows(result);

        return showsArrayList;
    }

    public Planning getCustomPlanning ()
    {
        _planningControler = new PlanningController();
        Planning p = _planningControler.getSavedPlanning();

        return p;
    }

    public Boolean setCustomPlanning(ArrayList<Representation> representations){

        _planningControler = new PlanningController();
        String result = _planningControler.saveCustomPlanning(representations);
        if (result.equals("")) {
            return false;
        }
        else
        {
            try {
                RequestManager.getInstance().post("/planning/save", result);
            } catch (IOException | ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

    }

    public void vote(Entity entity, int vote){

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

        try {
            RequestManager.getInstance().post("/vote", _jsonParser.parseVoteToJson(entity.getId(), type, vote));
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Planning generateBestPlanning(Date start,Date end, int nBPause, int tempsRepas)
    {
        _planningControler = new PlanningController();
        return  _planningControler.getBestPlanning(start,end,nBPause,tempsRepas);
    }

}
