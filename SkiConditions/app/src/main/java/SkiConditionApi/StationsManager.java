package SkiConditionApi;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import DbHelper.StationsDbOperations;

/**
 * Created by anael on 2015-04-02.
 */
public class StationsManager {

    public StationsManager(Context context)
    {
        _stations = new HashMap<String, Station>();
        _pendingFavoriteUpdate = new LinkedBlockingQueue<Station>();
        _dbOperations = new StationsDbOperations(context);
        new FavoriteService().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    public boolean LoadStations(Context context)
    {
        _stations = _dbOperations.ReadStations();
        return _stations.size() > 0;
    }

    public boolean SaveStations(Context context)
    {
        boolean isASuccess = _dbOperations.AddOrUpdateAllStations(this);
        return isASuccess;
    }

    public Boolean GetStationsFromWeb()
    {
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

    public HashMap<String, Station> GetFavorites()
    {
        HashMap<String, Station> favorites = new HashMap<String, Station>();
        for(Map.Entry<String, Station> entry : _stations.entrySet())
        {
            if(entry.getValue().get_favorite() == 1)
            {
                favorites.put(entry.getKey(), entry.getValue());
            }
        }

        return  favorites;
    }

    public void NotifyFavoriteChanged(Station station)
    {
        synchronized (_pendingFavoriteUpdate)
        {
            _stations.get(station.get_name()).set_favorite(station.get_favorite());
            _pendingFavoriteUpdate.add(station);
            _pendingFavoriteUpdate.notify();
        }
    }

    public int GetStationsNumber()
    {
        return _stations.size();
    }

    public HashMap<String, Station> get_stations() {
        return _stations;
    }

    private HashMap<String, Station> _stations;

    private LinkedBlockingQueue<Station> _pendingFavoriteUpdate;

    private StationsDbOperations _dbOperations;

    private class FavoriteService extends  AsyncTask<String, String, Boolean>{

        @Override
        protected Boolean doInBackground(String... params)
        {
            try {
                synchronized (_pendingFavoriteUpdate)
                {
                    while (_pendingFavoriteUpdate.isEmpty())
                    {
                        _pendingFavoriteUpdate.wait();
                        Station station = _pendingFavoriteUpdate.remove();
                        _dbOperations.SafeUpdateFavorite(station);
                    }

                }
            }
            catch(InterruptedException e)
            {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean saveIsASuccess) {

        }
    }
}
