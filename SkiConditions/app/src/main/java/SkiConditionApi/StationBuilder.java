package SkiConditionApi;

import android.util.Log;
import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by anael on 2015-04-02.
 */
public class StationBuilder {

    static Station BuildStation(JSONObject js)
    {
        Station station = null;
        try {
            String id = js.getString("id");
            String meteomediaID = js.getString("meteomediaID");
            String lastUpdate = js.getString("updated");
            String name = js.getString("name");

            HashMap<Integer, String> snowReports = BuildSnowReports(js);

            int acc24 = BuildAccumulation(js, "acc24");
            int acc48 = BuildAccumulation(js, "acc48");
            int acc7Days = BuildAccumulation(js, "acc7");
            int accSeason = BuildAccumulation(js, "accSeason");

            Pair<Integer, Integer> trails = BuildTrails(js);

            String snowQuality = js.getString("snow");
            String baseQuality = js.getString("base");
            String coverQuality = js.getString("cover");
            station = new Station(id, meteomediaID, lastUpdate, name, snowReports, acc24, acc48, acc7Days, accSeason, trails, snowQuality, baseQuality, coverQuality);
        }
        catch(JSONException e)
        {
            Log.d("StationBuilder", e.getMessage());
        }
        return station;
    }

    static private HashMap<Integer, String> BuildSnowReports(JSONObject js) throws JSONException
    {
        HashMap<Integer, String> snowReports = new HashMap<Integer, String>();
        for(int i=1; i<8; i++)
        {
            snowReports.put(i, js.getString("snow" + i));
        }

        return snowReports;
    }

    static Pair<Integer, Integer> BuildTrails(JSONObject js) throws JSONException
    {
        String trails = js.getString("trails");
        String openTrailsText = trails.substring(0, trails.indexOf('/'));
        String totalTrailsText = trails.substring(trails.indexOf('/')+1);

        openTrailsText = openTrailsText.replaceAll("\\s+","");
        totalTrailsText = totalTrailsText.replaceAll("\\s+","");

        int openTrails = Integer.parseInt(openTrailsText);
        int totalTrails = Integer.parseInt(totalTrailsText);

        return new Pair<Integer, Integer>(openTrails, totalTrails);
    }

    static int BuildAccumulation(JSONObject js, String type) throws JSONException
    {
        String accumulation = js.getString(type);
        accumulation = accumulation.replace("cm", "");
        accumulation = accumulation.replaceAll("\\s+","");
        int accumulationNumber = Integer.parseInt(accumulation);
        return accumulationNumber;
    }
}
