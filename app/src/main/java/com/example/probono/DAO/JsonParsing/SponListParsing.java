package com.example.probono.DAO.JsonParsing;

import com.example.probono.DTO.Spon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SponListParsing implements JsonParser<ArrayList<Spon>> {
    @Override
    public ArrayList<Spon> parse(String json) {

        if(json == null) return null;

        ArrayList<Spon> sponList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray sponArray = jsonObject.getJSONArray("spons");
            for(int i=0; i<sponArray.length(); i++) {
                JSONObject sponObject = sponArray.getJSONObject(i);
                Spon spon = new Spon();

                spon.setId(Integer.parseInt(sponObject.getString("id")));
                spon.setTitle(sponObject.getString("title"));
                spon.setDate(sponObject.getString("date"));
                spon.setContent(sponObject.getString("content"));
                spon.setVolunteer_institution(sponObject.getString("volunteer_institution"));

                sponList.add(spon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sponList;
    }
}
