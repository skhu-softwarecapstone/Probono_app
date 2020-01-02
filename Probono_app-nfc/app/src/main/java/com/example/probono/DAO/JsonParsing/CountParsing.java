package com.example.probono.DAO.JsonParsing;

import org.json.JSONException;
import org.json.JSONObject;

public class CountParsing implements JsonParser<Integer> {
    @Override
    public Integer parse(String json) {
        int count=0;
        try {
            JSONObject jsonObject = new JSONObject(json);
            count = jsonObject.getInt("count");

            return new Integer(count);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Integer(count);
    }
}
