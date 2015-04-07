package DbHelper;

import android.provider.BaseColumns;

/**
 * Created by anael on 4/3/2015.
 */
public final class StationsDbContract {

    public StationsDbContract(){}

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + StationEntry.TABLE_NAME + " (" +
            StationEntry.COLUMN_NAME_ENTRY_ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEP +
            StationEntry.COLUMN_NAME_METEOMEDIA_ID + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_LAST_UPDATE + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_STATION_NAME + TEXT_TYPE + " UNIQUE "+COMMA_SEP +
            StationEntry.COLUMN_NAME_SNOW1 + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_SNOW2 + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_SNOW3 + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_SNOW4 + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_SNOW5 + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_SNOW6 + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_SNOW7 + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_TEMPERATURE + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_WEATHER + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_ACC24 + INTEGER_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_ACC48 + INTEGER_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_ACC7DAYS + INTEGER_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_ACCSEASON + INTEGER_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_OPENTRAILS + INTEGER_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_TOTALTRAILS + INTEGER_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_SNOWQUALITY + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_BASEQUALITY + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_COVERQUALITY + TEXT_TYPE + COMMA_SEP +
            StationEntry.COLUMN_NAME_FAVORITE + INTEGER_TYPE +" )";

    public static String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + StationEntry.TABLE_NAME;

    public static abstract class StationEntry
    {
        public static final String TABLE_NAME = "meteostations";

        public static final String COLUMN_NAME_ENTRY_ID = "id";
        public static final String COLUMN_NAME_METEOMEDIA_ID = "meteomediaid";
        public static final String COLUMN_NAME_LAST_UPDATE = "lastupdate";
        public static final String COLUMN_NAME_STATION_NAME = "stationname";

        public static final String COLUMN_NAME_SNOW1 = "snow1";
        public static final String COLUMN_NAME_SNOW2 = "snow2";
        public static final String COLUMN_NAME_SNOW3 = "snow3";
        public static final String COLUMN_NAME_SNOW4 = "snow4";
        public static final String COLUMN_NAME_SNOW5 = "snow5";
        public static final String COLUMN_NAME_SNOW6 = "snow6";
        public static final String COLUMN_NAME_SNOW7 = "snow7";

        public static final String COLUMN_NAME_TEMPERATURE = "temperature";
        public static final String COLUMN_NAME_WEATHER = "weather";

        public static final String COLUMN_NAME_ACC24 = "acc24";
        public static final String COLUMN_NAME_ACC48 = "acc48";
        public static final String COLUMN_NAME_ACC7DAYS = "acc7days";
        public static final String COLUMN_NAME_ACCSEASON = "accseason";

        public static final String COLUMN_NAME_OPENTRAILS = "opentrails";
        public static final String COLUMN_NAME_TOTALTRAILS = "totaltrails";

        public static final String COLUMN_NAME_SNOWQUALITY = "snowquality";
        public static final String COLUMN_NAME_BASEQUALITY = "basequality";
        public static final String COLUMN_NAME_COVERQUALITY = "coverquality";

        public static final String COLUMN_NAME_FAVORITE = "favorite";
    }



}
