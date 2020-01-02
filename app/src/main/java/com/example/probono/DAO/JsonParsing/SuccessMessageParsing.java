package com.example.probono.DAO.JsonParsing;

import org.json.JSONException;
import org.json.JSONObject;

public class SuccessMessageParsing implements JsonParser<String> {
    @Override
    public String parse(String json) {
        String result =null;

        try {
            JSONObject jsonObject = new JSONObject(json);
            result = jsonObject.getString("successMessage");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
