package SkiConditionApi;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import DbHelper.StationsDbOperations;

/**
 * Created by anael on 2015-04-02.
 */
public class StationsManager {

    public boolean LoadStations(Context context)
    {
        StationsDbOperations operations = new StationsDbOperations(context);
        _stations = operations.ReadStations();
        return _stations.size() > 0;
    }

    public boolean SaveStations(Context context)
    {
        StationsDbOperations operations = new StationsDbOperations(context);
        boolean isASuccess = operations.AddOrUpdateAllStations(this);
        return isASuccess;
    }

    public Boolean GetStationsFromWeb()
    {
        _stations = new HashMap<String, Station>();
        JSONArray js = WebApiConnector.QueryStations();
        if(js == null)
        {
            return false;
        }

        for(int i=0; i<js.length(); i++)
        {

            Station station = null;
            try
            {
                station = StationBuilder.BuildStation(js.getJSONObject(i));
            }
            catch (JSONException e)
            {
                Log.d("StationManager", e.getMessage());
            }
            if(station != null)
            {
                _stations.put(station.get_name(), station);
            }
        }
        boolean result =_stations.size() >0;
        return result;
    }

    public int GetStationsNumber()
    {
        return _stations.size();
    }

    public HashMap<String, Station> get_stations() {
        return _stations;
    }

    private HashMap<String, Station> _stations;
}
