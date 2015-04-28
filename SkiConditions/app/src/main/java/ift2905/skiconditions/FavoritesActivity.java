package ift2905.skiconditions;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import UIHelper.StationsFavoritesAdapter;


public class FavoritesActivity extends SearchStationActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        adapter = new StationsFavoritesAdapter(((SkiConditionApplication)getApplication()).GetStationManager());
        setListAdapter(adapter);

        editText = (EditText)findViewById(R.id.searchStationText1);
        AddSearchListener();
    }

}
