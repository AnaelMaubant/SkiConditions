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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import SkiConditionApi.StationsManager;


public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        findViewById(R.id.mainSpinner1).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.loadingimage1)).setImageResource(R.mipmap.loadingmountain);
        _progressBar = ((ProgressBar)findViewById(R.id.mainSpinner1));
        _loadingText = ((TextView)findViewById(R.id.loadingprogresstext));
        _loadingText.setText("Loading stations from web ...");
        _progressStatus =1;
        _progressBar.setProgress(_progressStatus);
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
                _progressStatus ++;
                _progressBar.setProgress(_progressStatus);
                _loadingText.setText("Saving stations to database ...");
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
                _loadingText.setText("Loading stations from database ...");
                _progressStatus ++;
                _progressBar.setProgress(_progressStatus);
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
                _progressStatus ++;
                _progressBar.setProgress(_progressStatus);
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                LoadingActivity.this.finish();
            }
        }
    }

    private ProgressBar _progressBar;
    private TextView _loadingText;
    private int _progressStatus;

}
