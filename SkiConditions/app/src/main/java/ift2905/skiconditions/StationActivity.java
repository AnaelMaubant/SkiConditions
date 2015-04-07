package ift2905.skiconditions;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class StationActivity extends Activity {
    ImageButton imgButton1;
    ImageButton imgButton2;
    ImageButton imgButton3;
    ImageButton imgButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
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

}


