package ift2905.skiconditions;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import SkiConditionApi.WebApiConnector;


public class DebugActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
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

        return super.onOptionsItemSelected(item);
    }


private class DownloadStations extends AsyncTask<String, String, JSONArray> {

    @Override
    protected JSONArray doInBackground(String... params) {
        JSONArray js = WebApiConnector.QueryStations();
        return js;
    }

    @Override
    protected void onPostExecute(JSONArray js)
    {
        if(js == null)
        {
            Toast.makeText(DebugActivity.this, "Error while getting json", Toast.LENGTH_SHORT).show();
        }
        else
        {

            Toast.makeText(DebugActivity.this, "Json Parsing was successful", Toast.LENGTH_SHORT).show();

        }
    }
}
}
