package SkiConditionApi;

import android.util.Log;
import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created by anael on 2015-03-31.
 */
public class Station {

    public Station(int _id, String _meteoMediaId, String _lastUpdate, String _name,
                   HashMap<Integer, String> _snowReports, String _temperature, String _weather ,int _acc24, int _acc48, int _acc7Days, int _accSeason,
                   Pair<Integer, Integer> _trails, String _snowQuality, String _baseQuality, String _coverQuality, int _favorite)
    {
        this._coverQuality = _coverQuality;
        this._id = _id;
        this._meteoMediaId = _meteoMediaId;
        this._lastUpdate = _lastUpdate;
        this._name = _name;
        this._snowReports = _snowReports;
        this._temperature = _temperature;
        this._weather = _weather;
        this._acc24 = _acc24;
        this._acc48 = _acc48;
        this._acc7Days = _acc7Days;
        this._accSeason = _accSeason;
        this._trails = _trails;
        this._snowQuality = _snowQuality;
        this._baseQuality = _baseQuality;
        this._favorite = _favorite;
    }

    public int get_id() {
        return _id;
    }

    public String get_meteoMediaId() {
        return _meteoMediaId;
    }

    public String get_lastUpdate() {
        return _lastUpdate;
    }

    public String get_name() {
        return _name;
    }

    public HashMap<Integer, String> get_snowReports() {
        return _snowReports;
    }

    public int get_acc24() {
        return _acc24;
    }

    public int get_acc48() {
        return _acc48;
    }

    public int get_acc7Days() {
        return _acc7Days;
    }

    public int get_accSeason() {
        return _accSeason;
    }

    public Pair<Integer, Integer> get_trails() {
        return _trails;
    }

    public String get_snowQuality() {
        return _snowQuality;
    }

    public String get_baseQuality() {
        return _baseQuality;
    }

    public String get_coverQuality() {
        return _coverQuality;
    }

    public int get_favorite() { return _favorite; }

    public String get_temperature() { return _temperature; }

    public String get_weather() { return _weather; }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_meteoMediaId(String _meteoMediaId) {
        this._meteoMediaId = _meteoMediaId;
    }

    public void set_lastUpdate(String _lastUpdate) {
        this._lastUpdate = _lastUpdate;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_snowReports(HashMap<Integer, String> _snowReports) {
        this._snowReports = _snowReports;
    }

    public void set_acc24(int _acc24) {
        this._acc24 = _acc24;
    }

    public void set_acc48(int _acc48) {
        this._acc48 = _acc48;
    }

    public void set_acc7Days(int _acc7Days) {
        this._acc7Days = _acc7Days;
    }

    public void set_accSeason(int _accSeason) {
        this._accSeason = _accSeason;
    }

    public void set_trails(Pair<Integer, Integer> _trails) {
        this._trails = _trails;
    }

    public void set_snowQuality(String _snowQuality) {
        this._snowQuality = _snowQuality;
    }

    public void set_baseQuality(String _baseQuality) {
        this._baseQuality = _baseQuality;
    }

    public void set_coverQuality(String _coverQuality) {
        this._coverQuality = _coverQuality;
    }

    public void set_temperature(String _temperature) {
        this._temperature = _temperature;
    }

    public void set_weather(String _weather) {
        this._weather = _weather;
    }

    public void set_favorite(int _favorite) {
        this._favorite = _favorite;
    }

    private  int _id;
    private String _meteoMediaId;
    private String _lastUpdate;
    private String _name;

    private HashMap<Integer, String> _snowReports;
    private int _acc24;
    private int _acc48;
    private int _acc7Days;
    private int _accSeason;
    private Pair<Integer, Integer> _trails;

    private String _snowQuality;
    private String _baseQuality;
    private String _coverQuality;

    private String _temperature;
    private String _weather;

    private int _favorite;






}
