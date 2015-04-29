package ift2905.skiconditions;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
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
    Station mainStation;
    SharedPreferences.OnSharedPreferenceChangeListener listener;
    SharedPreferences prefs;

    StationsManager sm;
    HashMap<String, Station> stations;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        addButtonListener();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        CreatePrefsListener();
        sm = ((SkiConditionApplication)getApplication()).GetStationManager();
        stations = sm.get_stations();
        mainStation = GetMainStation();
        addTextListener();
        set_firstPage();
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

    Station GetMainStation()
    {
        String mainStationName = prefs.getString("welcome_station", "");
        mainStationName = mainStationName.replaceAll("\\s+$","");
        mainStationName = mainStationName.replaceAll("\n", "");
        return stations.get(mainStationName);
    }

    public void CreatePrefsListener()
    {
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                String mainStationName = sharedPreferences.getString(key, "");
                mainStationName = mainStationName.replaceAll("\\s+$","");
                mainStationName = mainStationName.replaceAll("\n", "");
                mainStation = stations.get(mainStationName);
                showResults();
            }
        };
        prefs.registerOnSharedPreferenceChangeListener(listener);
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


        t = mainStation.get_temperature();
        n = mainStation.get_name();
        p = mainStation.get_trails();
        w = mainStation.get_weather();
        d = mainStation.get_lastUpdate();

        // mettre ca dans l interface
        view_name.setText(mainStation.get_name());
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

                Intent intent = new Intent(MainActivity.this, SearchStationActivity.class);
                startActivity(intent);
            }
        });

        button_favoris = (ImageButton) findViewById(R.id.favorisButton);
        button_favoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
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
