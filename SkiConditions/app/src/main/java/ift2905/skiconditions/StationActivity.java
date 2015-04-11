package ift2905.skiconditions;

import android.app.Activity;
import android.content.Intent;
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

import SkiConditionApi.Station;


public class StationActivity extends Activity {
    ImageButton imgButton1,imgButton2,imgButton3,imgButton4;
    TextView view_name,view_temp,view_pistes,view_updateDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        findViews();
        getResults();
        addButtonListener();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_station, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addButtonListener() {
        imgButton1 = (ImageButton) findViewById(R.id.stationButton1);
        imgButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StationActivity.this, "Station Button1 is working!", Toast.LENGTH_SHORT).show();
            }
        });

        imgButton2 = (ImageButton) findViewById(R.id.stationButton2);
        imgButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StationActivity.this, "Station Button2 is working!", Toast.LENGTH_SHORT).show();
            }
        });

        imgButton3 = (ImageButton) findViewById(R.id.stationButton3);
        imgButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StationActivity.this, "Station Button3 is working!", Toast.LENGTH_SHORT).show();
            }
        });

        imgButton4 = (ImageButton) findViewById(R.id.stationButton4);
        imgButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StationActivity.this, "Station Button4 is working!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void findViews(){

        view_name = (TextView)findViewById(R.id.nomStation_activity2);
        view_temp= (TextView)findViewById(R.id.temperature_activity2);
        view_pistes= (TextView)findViewById(R.id.pistes_activity2);
        view_updateDate= (TextView)findViewById(R.id.emis_activity2);

    }

    public void getResults(){

        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("name");
        String temp = bundle.getString("temperature");
        String pistes = bundle.getString("pistes");
        String update = bundle.getString("update");

        // mettre ca dans l interface
        view_name.setText(name);
        view_temp.setText(temp);
        view_pistes.setText(pistes);
        view_updateDate.setText(update);

    }
}




