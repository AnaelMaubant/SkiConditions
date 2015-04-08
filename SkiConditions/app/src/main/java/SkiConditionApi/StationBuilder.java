package SkiConditionApi;

import android.database.Cursor;
import android.util.Log;
import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by anael on 2015-04-02.
 */
public class StationBuilder {

    public static Station BuildStation(JSONObject js)
    {
        Station station = null;
        try {
            int id = BuildId(js);
            String meteomediaID = js.getString("meteomediaID");
            String lastUpdate = js.getString("updated");
            String name = js.getString("name");

            HashMap<Integer, String> snowReports = BuildSnowReports(js);

            String temperature = js.getString("temperature");
            String weather = js.getString("weather");

            int acc24 = BuildAccumulation(js, "acc24");
            int acc48 = BuildAccumulation(js, "acc48");
            int acc7Days = BuildAccumulation(js, "acc7");
            int accSeason = BuildAccumulation(js, "accSeason");

            Pair<Integer, Integer> trails = BuildTrails(js);

            String snowQuality = js.getString("snow");
            String baseQuality = js.getString("base");
            String coverQuality = js.getString("cover");
            station = new Station(id, meteomediaID, lastUpdate, name, snowReports, temperature, weather,acc24, acc48, acc7Days, accSeason, trails, snowQuality, baseQuality, coverQuality, 0);
        }
        catch(JSONException e)
        {
            Log.d("StationBuilder", e.getMessage());
        }
        return station;
    }

    public static Station BuildStation(Cursor cursor)
    {
        Station station = null;
        int id = cursor.getInt(0);
        String meteomediaID = cursor.getString(1);
        String lastUpdate = cursor.getString(2);
        String name = cursor.getString(3);

        HashMap<Integer, String> snowReports = BuildSnowReports(cursor);

        String temperature = cursor.getString(11);
        String weather = cursor.getString(12);

        int acc24 = cursor.getInt(13);
        int acc48 = cursor.getInt(14);
        int acc7Days = cursor.getInt(15);
        int accSeason = cursor.getInt(16);

        Pair<Integer, Integer> trails = new Pair<Integer, Integer>(cursor.getInt(17), cursor.getInt(18));

        String snowQuality = cursor.getString(19);
        String baseQuality = cursor.getString(20);
        String coverQuality = cursor.getString(21);

        int favorite = cursor.getInt(22);
        station = new Station(id, meteomediaID, lastUpdate, name, snowReports, temperature, weather, acc24, acc48, acc7Days, accSeason, trails, snowQuality, baseQuality, coverQuality, favorite);

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

    static private HashMap<Integer, String> BuildSnowReports(Cursor cursor)
    {
        HashMap<Integer, String> snowReports = new HashMap<Integer, String>();
        for(int i=1; i<8; i++)
        {
            snowReports.put(i, cursor.getString(3+i));
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

    static  int BuildId(JSONObject js) throws JSONException
    {
        String id = js.getString("id");
        int idNumber = Integer.parseInt(id);
        return idNumber;
    }

}
