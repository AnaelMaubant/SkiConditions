package ift2905.skiconditions;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    ImageButton imgButton2;
    ImageButton imgButton3;
    ImageButton imgButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DownloadStations().execute();
        setContentView(R.layout.activity_main);
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
                Toast.makeText(MainActivity.this, "Json Parsing was successful", Toast.LENGTH_SHORT).show();
                new SaveStations().execute();
            }
        }
    }

    private class SaveStations extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            boolean isASuccess = ((SkiConditionApplication) getApplication()).GetStationManager().SaveStations(getBaseContext());
            return isASuccess;
        }

        @Override
        protected void onPostExecute(Boolean saveIsASuccess) {
            if (!saveIsASuccess) {
                Toast.makeText(MainActivity.this, "Error while saving stations", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Stations successfully save in db", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private class LoadStations extends AsyncTask<String, String, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {

            boolean isASuccess = ((SkiConditionApplication) getApplication()).GetStationManager().LoadStations(getBaseContext());
            return isASuccess;
        }

        @Override
        protected void onPostExecute(Boolean saveIsASuccess) {
            if (!saveIsASuccess) {
                Toast.makeText(MainActivity.this, "Error while loading stations", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Stations successfully load from db", Toast.LENGTH_SHORT).show();

            }
        }
    }


    public void addButtonListener() {
        ImageButton imgButton1 = (ImageButton) findViewById(R.id.chercherButton);
        imgButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "chercher une station!", Toast.LENGTH_SHORT).show();
            }
        });

        imgButton2 = (ImageButton) findViewById(R.id.favorisButton);
        imgButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "montrer tous mes sites preferees!", Toast.LENGTH_SHORT).show();
            }
        });

        imgButton3 = (ImageButton) findViewById(R.id.randomButton);
        imgButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "suggerer maintenant une station aleatoire!", Toast.LENGTH_SHORT).show();
            }
        });

        imgButton4 = (ImageButton) findViewById(R.id.mapButton);
        imgButton4.setOnClickListener(new View.OnClickListener() {
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
