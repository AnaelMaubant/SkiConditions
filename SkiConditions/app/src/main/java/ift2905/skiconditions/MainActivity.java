package ift2905.skiconditions;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import SkiConditionApi.Station;
import SkiConditionApi.StationsManager;


public class MainActivity extends ActionBarActivity {

    ImageButton button_search,button_favoris,button_random,button_map;
    TextView view_name,view_temp,view_pistes,view_updateDate;
    ImageView tempIcon;
    String t,n,w,d;
    Pair<Integer,Integer> p;
    Station s;

    //station affichiee dans la page d'accueil
    String stationPrincipal="Centre plein air Mont Kanasuta";

    StationsManager sm;
    HashMap<String, Station> stations;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        new DownloadStations().execute();

        addButtonListener();
        addTextListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.pref:
                Toast.makeText(getBaseContext(), "You selected Preference", Toast.LENGTH_SHORT).show();

                return true;

            case R.id.aide:
                Toast.makeText(getBaseContext(), "You selected Aide", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.info:
                Toast.makeText(getBaseContext(), "You selected Info ", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.testPage:
                StartStationActivity();
                Toast.makeText(getBaseContext(), "You selected Test Page", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_debug:
                StartDebugActivity();
                return true;
        }
        return true;
    }

    public void StartStationActivity() {
        Intent intent = new Intent(this, StationActivity.class);
        startActivity(intent);
    }

    public void StartDebugActivity() {
        Intent intent = new Intent(this, DebugActivity.class);
        startActivity(intent);
    }


    private class DownloadStations extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean loadIsASuccess = ((SkiConditionApplication) getApplication()).GetStationManager().GetStationsFromWeb();
            return loadIsASuccess;
        }

        @Override
        protected void onPostExecute(Boolean loadIsASuccess) {
            if (!loadIsASuccess) {
                Toast.makeText(MainActivity.this, "Error while getting json", Toast.LENGTH_SHORT).show();
                new LoadStations().execute();
            } else {
                Log.d("MAIN", "Json Parsing was successful");
                Toast.makeText(MainActivity.this, "Json Parsing was successful", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, "Error while saving stations", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Stations successfully save in db", Toast.LENGTH_SHORT).show();

                new LoadStations().execute();

                Log.d("MAIN", "Stations successfully save in db");
                stations = sm.get_stations();
                set_firstPage();

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
                Toast.makeText(MainActivity.this, "Error while loading stations", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("MAIN", "Stations successfully load from db");
                Toast.makeText(MainActivity.this, "Stations successfully load from db", Toast.LENGTH_SHORT).show();
                stations = sm.get_stations();
                set_firstPage();

            }
        }
    }

    private void findViews(){
        view_name = (TextView)findViewById(R.id.nomStation);
        view_temp = (TextView)findViewById(R.id.temp);
        view_pistes= (TextView)findViewById(R.id.pistes);
        tempIcon=(ImageView)findViewById(R.id.tempIcon);
        view_updateDate= (TextView)findViewById(R.id.emis);

    }

    //first page
    private void showResults(){
        // on recupere la premiere station
        s = stations.get(stationPrincipal);
        t = s.get_temperature();
        n = s.get_name();
        p = s.get_trails();
        w = s.get_weather();
        d = s.get_lastUpdate();

        // mettre ca dans l interface
        view_name.setText(s.get_name());
        view_temp.setText(t);
        view_pistes.setText(p.first + "/" + p.second);
        view_updateDate.setText(d);
    }

    public void set_firstPage(){
        findViews();
        showResults();
    }

    public void addButtonListener() {
        button_search = (ImageButton) findViewById(R.id.chercherButton);
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Toast.makeText(MainActivity.this, "chercher une station!", Toast.LENGTH_SHORT).show();
            }
        });

        button_favoris = (ImageButton) findViewById(R.id.favorisButton);
        button_favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "montrer tous mes sites preferees!", Toast.LENGTH_SHORT).show();
            }
        });

        button_random = (ImageButton) findViewById(R.id.randomButton);
        button_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "suggerer maintenant une station aleatoire!", Toast.LENGTH_SHORT).show();
            }
        });

        button_map = (ImageButton) findViewById(R.id.mapButton);
        button_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "montrer la carte qui indique tous les stations!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addTextListener() {
        TextView btn = (TextView) findViewById(R.id.nomStation);
        btn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick (View v){
            Toast.makeText(MainActivity.this, "Afficher maintenant la page de details de cette station!!", Toast.LENGTH_SHORT).show();

        }
    }

    );

}

}
