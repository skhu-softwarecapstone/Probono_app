package com.example.probono.DAO.JsonParsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountJsonParsing {
    private String json;

    public CountJsonParsing(String json) {this.json = json;}

    public int jsonParsing() {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int count = jsonObject.getInt("count");
            return count;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
