package ift2905.skiconditions;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import UIHelper.StationsAdapter;


public class DebugListActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new StationsAdapter(((SkiConditionApplication)getApplication()).GetStationManager()));
    }
}
