package SkiConditionApi;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;

/**
 * Created by anael on 2015-03-31.
 */
 public class WebApiConnector {

    static public HttpEntity GetHttpPage(String url) throws IOException
    {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet http = new HttpGet(url);
        HttpResponse response = httpClient.execute(http);
        return response.getEntity();
    }

    static public JSONArray CreateJson(HttpEntity page) throws IOException, JSONException
    {
        String jsonText = EntityUtils.toString(page, HTTP.UTF_8);
        JSONArray js = new JSONArray(jsonText);
        return js;
    }

    static public JSONArray QueryStations()
    {
        JSONArray js = null;
        try
        {
            js = CreateJson(GetHttpPage(webApiUrl));
            Log.d("WebApiConnector", "Get json was successful");
        }
        catch(IOException ioExcep)
        {
            Log.d("WebApiConnector", ioExcep.getMessage());
        }
        catch(JSONException jsonExcep)
        {
            Log.d("WebApiConnector", jsonExcep.getMessage());
        }
        return js;
    }



    static final String webApiUrl = "http://step.polymtl.ca/~morathyl/";
}
