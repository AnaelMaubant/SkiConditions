package ift2905.skiconditions;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import UIHelper.StationsAdapter;


public class SearchStationActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_searchstations);
        adapter = new StationsAdapter(((SkiConditionApplication)getApplication()).GetStationManager());
        setListAdapter(adapter);

        editText = (EditText)findViewById(R.id.searchStationText);
        AddSearchListener();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        adapter = new StationsAdapter(((SkiConditionApplication)getApplication()).GetStationManager());
        setListAdapter(adapter);
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

                SearchStationActivity.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    protected StationsAdapter adapter;
    protected EditText editText;
}
