package UIHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

import SkiConditionApi.Station;
import SkiConditionApi.StationsManager;
import ift2905.skiconditions.R;

/**
 * Created by anael on 4/5/2015.
 */
public class StationsAdapter extends BaseAdapter implements Filterable{

    public StationsAdapter(StationsManager stations)
    {
        _data = new ArrayList<Map.Entry<String, Station>>();
        _data.addAll(stations.get_stations().entrySet());

        _stationFilterList = _data;
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

        ((TextView) result.findViewById(R.id.temperatureText)).setText(station.getValue().get_temperature() + "°C");
        ((TextView) result.findViewById(R.id.StationNameText)).setText(station.getKey());
        ((TextView) result.findViewById(R.id.trailsText)).setText(station.getValue().get_trails().first + "/" + station.getValue().get_trails().second);

        CheckBox favoriteBox = (CheckBox)result.findViewById(R.id.favorite_checkbox);

        ImageView image = (ImageView)result.findViewById(R.id.weatherimage);

        if(station.getValue().get_weather().equals("sunny"))
        {
            image.setImageResource(R.mipmap.sunnyicon);
        }
        else if(station.getValue().get_weather().equals("storm-clouds"))
        {
            image.setImageResource(R.mipmap.snowicon);
        }
        else
        {
            image.setImageResource(R.mipmap.cloudyicon);
        }



        return result;
    }

    @Override
    public Filter getFilter()
    {
        if(listViewFilter == null)
        {
            listViewFilter = new ValueFilter();
        }
        return listViewFilter;
    }

    private class ValueFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();

            if(constraint!=null && constraint.length()>0)
            {
                ArrayList<Map.Entry<String, Station>> filterList = new ArrayList<Map.Entry<String, Station>>();
                for(int i =0; i<_stationFilterList.size(); i++)
                {
                    if(_stationFilterList.get(i).getKey().toUpperCase().contains(constraint.toString().toUpperCase()))
                    {
                        filterList.add(_stationFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            }
            else
            {
                results.count = _stationFilterList.size();
                results.values = _stationFilterList;
            }
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            _data = (ArrayList<Map.Entry<String, Station>>)results.values;
            notifyDataSetChanged();
        }
    }
    private ArrayList<Map.Entry<String, Station>> _data;
    private ValueFilter listViewFilter;
    private ArrayList<Map.Entry<String, Station>> _stationFilterList;
}
