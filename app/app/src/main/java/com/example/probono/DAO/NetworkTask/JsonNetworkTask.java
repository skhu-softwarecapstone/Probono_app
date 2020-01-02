package com.example.probono.DAO.NetworkTask;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.probono.DAO.JsonParsing.JsonParser;
import com.example.probono.DAO.RequestHttpURLConnection;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public abstract class JsonNetworkTask<DTO> extends AsyncTask<Void, Void, String>{

    protected String url;
    protected JsonParser parser;
    protected JSONObject param;

    public JsonNetworkTask(String url, JSONObject param, JsonParser<DTO> parser){
        this.url = url;
        this.parser = parser;
        this.param = param;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, param); //해당 URL로부터 결과물을 얻어온다.


        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        if(s==null) postTask(null);
        else postTask((DTO)parser.parse(s));
    }

    abstract public void postTask(DTO result);

}
