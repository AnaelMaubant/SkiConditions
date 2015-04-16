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

import java.util.HashMap;

import SkiConditionApi.Station;


public class StationActivity extends Activity {
    ImageButton imgButton1,imgButton2,imgButton3,imgButton4;
    TextView view_name,view_temp,view_pistes,view_updateDate,view_profondeur,view_neigeDerniere,view_condition;
    TextView view_snowReport1, view_snowReport2,view_snowReport3,view_snowReport4,view_snowReport5,view_snowReport6,view_snowReport7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        findViews();
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

        view_updateDate= (TextView)findViewById(R.id.emis_activity2);
        view_name = (TextView)findViewById(R.id.nomStation_activity2);
        view_temp= (TextView)findViewById(R.id.temperature_activity2);
        view_pistes= (TextView)findViewById(R.id.pistes_activity2);
        view_profondeur= (TextView)findViewById(R.id.profondeur);
        view_neigeDerniere=(TextView)findViewById(R.id.derniere_neige);
        view_condition=(TextView)findViewById(R.id.conditionSki);
        view_snowReport1=(TextView)findViewById(R.id.snow1);
        view_snowReport2=(TextView)findViewById(R.id.snow2);
        view_snowReport3=(TextView)findViewById(R.id.snow3);
        view_snowReport4=(TextView)findViewById(R.id.snow4);
        view_snowReport5=(TextView)findViewById(R.id.snow5);
        view_snowReport6=(TextView)findViewById(R.id.snow6);
        view_snowReport7=(TextView)findViewById(R.id.snow7);



    }

    public void setData(Station s){

        String name = s.get_name();
        String temp = s.get_temperature();
        String pistes = s.get_trails().first + "/" + s.get_trails().second;
        String update = s.get_lastUpdate();
        int profondeur= s.get_accSeason();
        int acc24=s.get_acc24();
        String cond_ski=s.get_snowQuality();
        HashMap<Integer, String> snowreport =s.get_snowReports();

        // mettre ca dans l'interface
        view_name.setText(name);
        view_temp.setText(temp);
        view_pistes.setText(pistes);
        view_updateDate.setText(update);
        view_profondeur.setText(profondeur+" cm");
        view_neigeDerniere.setText(acc24+" cm");
        view_condition.setText(cond_ski);
        view_snowReport1.setText(snowreport.get("snow1"));
        view_snowReport2.setText(snowreport.get("snow2"));
        view_snowReport3.setText(snowreport.get("snow3"));
        view_snowReport4.setText(snowreport.get("snow4"));
        view_snowReport5.setText(snowreport.get("snow5"));
        view_snowReport6.setText(snowreport.get("snow6"));
        view_snowReport7.setText(snowreport.get("snow7"));

    }
}




