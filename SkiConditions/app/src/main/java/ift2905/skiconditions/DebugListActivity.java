package ift2905.skiconditions;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import UIHelper.StationsAdapter;


public class DebugListActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug_list);
        adapter = new StationsAdapter(((SkiConditionApplication)getApplication()).GetStationManager());
        setListAdapter(adapter);

        editText = (EditText)findViewById(R.id.searchStationText);
        AddSearchListener();
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
