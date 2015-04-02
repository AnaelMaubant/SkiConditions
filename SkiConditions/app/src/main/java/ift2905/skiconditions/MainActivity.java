package ift2905.skiconditions;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        switch(item.getItemId()) {
            case R.id.pref:

                Toast.makeText(getBaseContext(), "You selected Preference", Toast.LENGTH_SHORT).show();
                break;

            case R.id.aide:
                Toast.makeText(getBaseContext(), "You selected Aide", Toast.LENGTH_SHORT).show();
                break;

            case R.id.info:
                Toast.makeText(getBaseContext(), "You selected Info ", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;

    }
}
