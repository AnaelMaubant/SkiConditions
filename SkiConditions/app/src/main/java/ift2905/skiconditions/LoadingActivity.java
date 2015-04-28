package ift2905.skiconditions;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import SkiConditionApi.StationsManager;


public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        findViewById(R.id.mainSpinner1).setVisibility(View.VISIBLE);
        new DownloadStations().execute();
    }

    StationsManager sm;

    private class DownloadStations extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean loadIsASuccess = ((SkiConditionApplication) getApplication()).GetStationManager().GetStationsFromWeb();
            return loadIsASuccess;
        }

        @Override
        protected void onPostExecute(Boolean loadIsASuccess) {
            if (!loadIsASuccess) {
                Toast.makeText(LoadingActivity.this, "Error while getting json", Toast.LENGTH_SHORT).show();
                new LoadStations().execute();
            } else {
                Log.d("MAIN", "Json Parsing was successful");
                Toast.makeText(LoadingActivity.this, "Json Parsing was successful", Toast.LENGTH_SHORT).show();
                new SaveStations().execute();
            }
        }
    }

    private class SaveStations extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            sm = ((SkiConditionApplication) getApplication()).GetStationManager();
            boolean isASuccess = sm.SaveStations(getBaseContext());
            return isASuccess;
        }

        @Override
        protected void onPostExecute(Boolean saveIsASuccess) {
            if (!saveIsASuccess) {
                Toast.makeText(LoadingActivity.this, "Error while saving stations", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoadingActivity.this, "Stations successfully save in db", Toast.LENGTH_SHORT).show();

                new LoadStations().execute();
            }
        }
    }

    private class LoadStations extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            sm = ((SkiConditionApplication) getApplication()).GetStationManager();
            boolean isASuccess = sm.LoadStations(getBaseContext());
            return isASuccess;
        }

        @Override
        protected void onPostExecute(Boolean saveIsASuccess) {
            if (!saveIsASuccess) {
                Toast.makeText(LoadingActivity.this, "Error while loading stations", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoadingActivity.this, "Stations successfully load from db", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                LoadingActivity.this.finish();
            }
        }
    }

}
