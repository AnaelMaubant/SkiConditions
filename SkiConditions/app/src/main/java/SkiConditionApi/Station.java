package SkiConditionApi;

import android.util.Pair;

import java.util.Vector;

/**
 * Created by anael on 2015-03-31.
 */
public class Station {

    private  String _id;
    private String _meteoMediaId;
    private String _lastUpdate;

    private Vector<String> _snowReports;
    private int _acc24;
    private int _acc48;
    private int _acc7Days;
    private int _accSeason;
    private Pair<Integer, Integer> _trails;



}
