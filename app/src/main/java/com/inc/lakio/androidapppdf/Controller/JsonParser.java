package com.inc.lakio.androidapppdf.Controller;

import com.inc.lakio.androidapppdf.Model.Planning;
import com.inc.lakio.androidapppdf.Model.Representation;
import com.inc.lakio.androidapppdf.Model.Show;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Lakio on 15/06/2015.
 */
public class JsonParser {

    public String parsePlanningInfoToJson(Date start, Date end, int nBPause, int pauseRepas) {
        JSONObject obj = new JSONObject();

        try {
            obj.put("startSchedule", start.getTime());
            obj.put("endSchedule", end.getTime());
            obj.put("nbPause", nBPause);
            obj.put("pauseRepas", pauseRepas);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj.toString();

    }

    public ArrayList<Show> parseToShows(String jsonString) {
        ArrayList<Show> resultList = new ArrayList<>();
        ArrayList<Representation> representationList = null;

        JSONArray json;

        if (jsonString != null) {

            try {
                json = null;
                json = new JSONArray(jsonString);

                for (int i = 0; i < json.length(); i++) {
                    representationList = new ArrayList<>();
                    JSONObject item = null;
                            item = json.getJSONObject(i);
                    Show show = new Show();
                    show.setId(item.getInt("id"));
                    show.setName(item.getString("name"));
                    show.setDescription(item.getString("description"));
                    show.setAverageNote(item.getDouble("averageNote"));
                    show.setTotalVote(item.getInt("totalVote"));
                    JSONObject t = null;
                            t = new JSONObject(item.getString("location"));

                    show.setLocationTag(t.getString("tag"));
                    show.setLongitude(t.getDouble("longitude"));
                    show.setLatitude(t.getDouble("latitude"));

                    show.setDuration(item.getLong("duration"));


                    JSONArray schedules = null;
                            schedules = new JSONArray(item.getString("schedulesList"));

                    for (int j = 0; j < schedules.length(); j++) {
                        JSONObject obj = null;
                                obj = schedules.getJSONObject(j);
                        Date d = new Date();
                        d.setTime(obj.getLong("time") * 1000);
                        Representation representation = new Representation(show.getId(), d);
                        representation.set_locationTag(show.getLocationTag());
                        representation.set_name(show.getName());
                        representationList.add(representation);
                    }

                    show.setSchedules(representationList);

                    resultList.add(show);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }

        return resultList;
    }

    public Planning parseToPlanning(String jsonString) {
        Planning planning = new Planning();

        JSONArray json = null;

        try {

            json = new JSONArray(jsonString);

            for (int i = 0; i < json.length(); i++) {
                JSONObject item = json.getJSONObject(i);

                planning.setId(item.getInt("id"));

                Date d = new Date();
                d.setTime(item.getLong("startAt"));
                planning.setStartAt(d);

                d.setTime(item.getLong("endAt"));
                planning.setEndAt(d);

                planning.setRepresentationList(parseToRepresentation(item.getString("representationList")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return planning;
    }

    public ArrayList<Representation> parseToRepresentation(String _jsonString) {
        ArrayList<Representation> resultList = new ArrayList<>();
        JSONArray json = null;

        try {
            json = new JSONArray(_jsonString);
            for (int i = 0; i < json.length(); i++) {
                JSONObject item = json.getJSONObject(i);

                Date d = new Date();
                d.setTime(item.getLong("schedule"));
                Representation representation = new Representation(item.getInt("idShow"), d);
                representation.set_name(item.getString("name"));
                representation.set_locationTag(item.getString("location"));

                resultList.add(representation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public String parseVoteToJson(int id, String type, int vote) {

        JSONObject obj = new JSONObject();

        try {
            obj.put("id", id);
            obj.put("vote", vote);
            obj.put("type", type);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj.toString();
    }

    public String parsePlanningToJson(Planning planing)
    {
        JSONObject obj = new JSONObject();

        try {
            obj.put("start",planing.getStartAt().getTime());
            //obj.put("stop",planing.getEndAt().getTime());
            obj.put("login",planing.getLoginUser());
            JSONArray _array = new JSONArray();
            for (int i = 0; i<planing.getRepresentationList().size();i++)
            {
                Representation tmp = planing.getRepresentationList().get(i);
                JSONObject jsontmp = new JSONObject();
                jsontmp.put("location",tmp.get_locationTag());
                jsontmp.put("name",tmp.get_name());
                jsontmp.put("idshow",tmp.getIdShow());
                jsontmp.put("horaire",tmp.getSchedule());
                _array.put(jsontmp);
            }
            obj.put("representation",_array);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return obj.toString();
    }
}
