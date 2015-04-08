package ift2905.skiconditions;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import DbHelper.StationsDbOperations;
import SkiConditionApi.StationsManager;
import SkiConditionApi.WebApiConnector;


public class DebugActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        _stationManager = ((SkiConditionApplication)getApplication()).GetStationManager();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_debug, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            new DownloadStations().execute();
            return true;
        }
        else if (id == R.id.Debug_load)
        {
            new LoadStations().execute();
            return true;
        }
        else if (id == R.id.Go_to_list)
        {
            Intent intent = new Intent(this, DebugListActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    private class DownloadStations extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean loadIsASuccess = _stationManager.GetStationsFromWeb();
            return loadIsASuccess;
        }

        @Override
        protected void onPostExecute(Boolean loadIsASuccess) {
            if (!loadIsASuccess) {
                Toast.makeText(DebugActivity.this, "Error while getting json", Toast.LENGTH_SHORT).show();
                new LoadStations().execute();
            } else {
                Toast.makeText(DebugActivity.this, "Json Parsing was successful", Toast.LENGTH_SHORT).show();
                new SaveStations().execute();
            }
        }
    }

    private class SaveStations extends  AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params)
        {

            boolean isASuccess = _stationManager.SaveStations(getBaseContext());
            return isASuccess;
        }

        @Override
        protected void onPostExecute(Boolean saveIsASuccess) {
            if (!saveIsASuccess) {
                Toast.makeText(DebugActivity.this, "Error while saving stations", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DebugActivity.this, "Stations successfully save in db", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private class LoadStations extends  AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params)
        {

            boolean isASuccess = _stationManager.LoadStations(getBaseContext());
            return isASuccess;
        }

        @Override
        protected void onPostExecute(Boolean saveIsASuccess) {
            if (!saveIsASuccess) {
                Toast.makeText(DebugActivity.this, "Error while loading stations", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DebugActivity.this, "Stations successfully load from db", Toast.LENGTH_SHORT).show();

            }
        }
    }

    StationsManager _stationManager;
}
