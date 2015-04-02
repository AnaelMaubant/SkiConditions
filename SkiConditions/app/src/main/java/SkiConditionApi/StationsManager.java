package SkiConditionApi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by anael on 2015-04-02.
 */
public class StationsManager {

    void LoadStations()
    {}

    void SaveStations()
    {}

    public Boolean GetStationsFromWeb()
    {
        _stations = new HashMap<String, Station>();
        JSONArray js = WebApiConnector.QueryStations();

        for(int i=0; i<js.length(); i++)
        {

            Station station = null;
            try
            {
                station = StationBuilder.BuildStation(js.getJSONObject(i));
            } catch (JSONException e)
            {
                Log.d("StationManager", e.getMessage());
            }
            if(station != null)
            {
                _stations.put(station.get_name(), station);
            }
        }
        return _stations.size() >0;
    }

    private HashMap<String, Station> _stations;
}
