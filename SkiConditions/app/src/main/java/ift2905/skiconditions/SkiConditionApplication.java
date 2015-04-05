package ift2905.skiconditions;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import SkiConditionApi.StationsManager;

/**
 * Created by anael on 4/5/2015.
 */
public class SkiConditionApplication extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
        _stationManager = new StationsManager();
    }

    public StationsManager GetStationManager()
    {
        return _stationManager;
    }


    private StationsManager _stationManager;
}
