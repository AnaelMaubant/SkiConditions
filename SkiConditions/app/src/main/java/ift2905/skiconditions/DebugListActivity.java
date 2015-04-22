package ift2905.skiconditions;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import UIHelper.StationsAdapter;


public class DebugListActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_list);
        adapter = new StationsAdapter(((SkiConditionApplication)getApplication()).GetStationManager());
        setListAdapter(adapter);
        ListView lw = getListView();

        editText = (EditText)findViewById(R.id.searchStationText);
        AddSearchListener();
    }

    @Override
    public void onListItemClick(ListView parent, View w, int position, long id)
    {
        String stationName = adapter.getItem(position).getKey();
        Intent intent = new Intent(this, StationActivity.class);
        Bundle b = new Bundle();
        b.putString("Station name", stationName);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void AddSearchListener()
    {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                DebugListActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private StationsAdapter adapter;
    private EditText editText;
}
