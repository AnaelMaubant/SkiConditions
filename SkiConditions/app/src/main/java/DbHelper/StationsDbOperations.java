package DbHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

import SkiConditionApi.Station;
import SkiConditionApi.StationBuilder;
import SkiConditionApi.StationsManager;

/**
 * Created by anael on 4/4/2015.
 */
public class StationsDbOperations {

    public StationsDbOperations(Context context)
    {
        _dbHandler = new StationDbHandler(context);
    }

    public void OpenDb()
    {
        _db = _dbHandler.getWritableDatabase();
    }

    public void CloseDb()
    {
        _db.close();
    }

    private long AddStation(Station station)
    {
        OpenDb();

        ContentValues values = new ContentValues();
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_ENTRY_ID, station.get_id());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_METEOMEDIA_ID, station.get_meteoMediaId());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_LAST_UPDATE, station.get_lastUpdate());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_STATION_NAME, station.get_name());

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW1, station.get_snowReports().get(1));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW2, station.get_snowReports().get(2));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW3, station.get_snowReports().get(3));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW4, station.get_snowReports().get(4));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW5, station.get_snowReports().get(5));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW6, station.get_snowReports().get(6));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW7, station.get_snowReports().get(7));

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_TEMPERATURE, station.get_temperature());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_WEATHER, station.get_weather());

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_ACC24, station.get_acc24());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_ACC48, station.get_acc48());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_ACC7DAYS, station.get_acc7Days());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_ACCSEASON, station.get_accSeason());

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_OPENTRAILS, station.get_trails().first);
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_TOTALTRAILS, station.get_trails().second);

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOWQUALITY, station.get_snowQuality());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_BASEQUALITY, station.get_baseQuality());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_COVERQUALITY, station.get_coverQuality());

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_FAVORITE, station.get_favorite());

        long insertedID = _db.insertWithOnConflict(StationsDbContract.StationEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        CloseDb();

        return insertedID;
    }

    private boolean UpdateStation(Station station)
    {
        OpenDb();
        ContentValues values = new ContentValues();

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_LAST_UPDATE, station.get_lastUpdate());

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW1, station.get_snowReports().get(1));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW2, station.get_snowReports().get(2));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW3, station.get_snowReports().get(3));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW4, station.get_snowReports().get(4));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW5, station.get_snowReports().get(5));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW6, station.get_snowReports().get(6));
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOW7, station.get_snowReports().get(7));

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_TEMPERATURE, station.get_temperature());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_WEATHER, station.get_weather());

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_ACC24, station.get_acc24());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_ACC48, station.get_acc48());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_ACC7DAYS, station.get_acc7Days());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_ACCSEASON, station.get_accSeason());

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_OPENTRAILS, station.get_trails().first);
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_TOTALTRAILS, station.get_trails().second);

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_SNOWQUALITY, station.get_snowQuality());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_BASEQUALITY, station.get_baseQuality());
        values.put(StationsDbContract.StationEntry.COLUMN_NAME_COVERQUALITY, station.get_coverQuality());

        String selection = StationsDbContract.StationEntry.COLUMN_NAME_ENTRY_ID + " == ? AND " +
                StationsDbContract.StationEntry.COLUMN_NAME_STATION_NAME + " == ?";

        String[] selectionArgs = {Integer.toString(station.get_id()), station.get_name()};

        int count = _db.update(StationsDbContract.StationEntry.TABLE_NAME, values, selection, selectionArgs);

        CloseDb();

        return count > 0;
    }

    private boolean StationExists(Station station)
    {
        OpenDb();

        String[] projection = {StationsDbContract.StationEntry.COLUMN_NAME_ENTRY_ID};

        String selection = StationsDbContract.StationEntry.COLUMN_NAME_ENTRY_ID + " == ? AND " +
                StationsDbContract.StationEntry.COLUMN_NAME_STATION_NAME + " == ?";

        String[] selectionArgs = {Integer.toString(station.get_id()), station.get_name()};

        String sortOrder = StationsDbContract.StationEntry.COLUMN_NAME_ENTRY_ID + " DESC";

        Cursor cursor = _db.query(StationsDbContract.StationEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

        return cursor.getCount() == 1;

    }

    private boolean UpdateFavorite(Station station)
    {
        OpenDb();
        ContentValues values = new ContentValues();

        values.put(StationsDbContract.StationEntry.COLUMN_NAME_FAVORITE, station.get_favorite());

        String selection = StationsDbContract.StationEntry.COLUMN_NAME_ENTRY_ID + " == ? AND " +
                StationsDbContract.StationEntry.COLUMN_NAME_STATION_NAME + " == ?";

        String[] selectionArgs = {Integer.toString(station.get_id()), station.get_name()};

        int count = _db.update(StationsDbContract.StationEntry.TABLE_NAME, values, selection, selectionArgs);

        CloseDb();

        return count > 0;


    }

    public boolean SafeUpdateFavorite(Station station)
    {
        if(StationExists(station))
        {
            boolean isUpdated = UpdateFavorite(station);
            if(!isUpdated)
            {
                return false;
            }
        }
        else
        {
            return false;
        }
        return true;
    }

    public Boolean AddOrUpdateAllStations(StationsManager stationsManager)
    {
        for(Map.Entry<String, Station> stationEntry : stationsManager.get_stations().entrySet())
        {
            Station station = stationEntry.getValue();

            if(StationExists(station))
            {
                boolean isUpdated = UpdateStation(station);
                if(!isUpdated)
                {
                    return false;
                }
            }
            else
            {
                long insertionID = AddStation(station);
                if(insertionID != station.get_id())
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Boolean AddAllStationsClear(StationsManager stationsManager)
    {
        DeleteStations();
        for(Map.Entry<String, Station> stationEntry : stationsManager.get_stations().entrySet())
        {
            Station station = stationEntry.getValue();
            long insertionID = AddStation(station);
            if(insertionID != station.get_id())
            {
                return false;
            }
        }
        return true;
    }

    public void DeleteStations()
    {
        OpenDb();
        _db.execSQL("DELETE from " + StationsDbContract.StationEntry.TABLE_NAME);
        _db.execSQL("VACUUM");
        CloseDb();
    }

    public HashMap<String, Station> ReadStations()
    {
        HashMap<String, Station> stations = new HashMap<String, Station>();
        OpenDb();

        Cursor cursor = _db.rawQuery("select * from " + StationsDbContract.StationEntry.TABLE_NAME, null);
        while(cursor.moveToNext())
        {
            Station station = StationBuilder.BuildStation(cursor);
            stations.put(station.get_name(), station);
        }

        CloseDb();
        return stations;
    }

    private SQLiteDatabase _db;
    private StationDbHandler _dbHandler;
}
