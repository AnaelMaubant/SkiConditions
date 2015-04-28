package UIHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import SkiConditionApi.Station;
import SkiConditionApi.StationsManager;
import ift2905.skiconditions.R;

/**
 * Created by anael on 4/27/2015.
 */
public class StationsFavoritesAdapter extends StationsAdapter{

    public StationsFavoritesAdapter(StationsManager stations)
    {
        _stationManager = stations;
        _data = new ArrayList<Map.Entry<String, Station>>();
        _data.addAll(_stationManager.GetFavorites().entrySet());

        _stationFilterList = _data;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final View result;
        if (convertView == null)
        {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.stationsfavoritesadapter, parent, false);
        }
        else
        {
            result = convertView;
        }

        final Map.Entry<String, Station> station = getItem(position);

        ((TextView) result.findViewById(R.id.temperatureText1)).setText(station.getValue().get_temperature() + "°C");
        ((TextView) result.findViewById(R.id.StationNameText1)).setText(station.getKey());
        ((TextView) result.findViewById(R.id.trailsText1)).setText(station.getValue().get_trails().first + "/" + station.getValue().get_trails().second);

        CheckBox favoriteBox = (CheckBox)result.findViewById(R.id.favorite_checkbox1);

        favoriteBox.setChecked(true);


        ImageView image = (ImageView)result.findViewById(R.id.weatherimage1);

        if(station.getValue().get_weather().equals("sunny"))
        {
            image.setImageResource(R.mipmap.sunnyicon);
        }
        else if(station.getValue().get_weather().equals("storm-clouds") && station.getValue().get_temperature().contains("-"))
        {
            image.setImageResource(R.mipmap.snowicon);
        }
        else
        {
            image.setImageResource(R.mipmap.cloudyicon);
        }

        return result;
    }


}
