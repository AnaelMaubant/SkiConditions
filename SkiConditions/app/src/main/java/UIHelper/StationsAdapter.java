package UIHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import SkiConditionApi.Station;
import SkiConditionApi.StationsManager;
import ift2905.skiconditions.R;

/**
 * Created by anael on 4/5/2015.
 */
public class StationsAdapter extends BaseAdapter{

    public StationsAdapter(StationsManager stations)
    {
        _data = new ArrayList();
        _data.addAll(stations.get_stations().entrySet());
    }

    @Override
    public int getCount()
    {
        return _data.size();
    }

    @Override
    public Map.Entry<String, Station> getItem(int position)
    {
        return (Map.Entry)_data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return ((Map.Entry<String, Station>)_data.get(position)).getValue().get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final View result;
        if (convertView == null)
        {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.stationsadapter, parent, false);
        }
        else
        {
            result = convertView;
        }

        Map.Entry<String, Station> station = getItem(position);

        ((TextView) result.findViewById(android.R.id.text1)).setText(Integer.toString(station.getValue().get_id()));
        ((TextView) result.findViewById(android.R.id.text2)).setText(station.getKey());

        return result;
    }
    private ArrayList _data;
}
