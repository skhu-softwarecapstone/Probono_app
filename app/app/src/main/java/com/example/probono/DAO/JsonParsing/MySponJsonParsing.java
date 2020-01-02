package com.example.probono.DAO.JsonParsing;

import com.example.probono.DTO.MySpon;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MySponJsonParsing {
    private String json;

    public MySponJsonParsing(String json) {this.json = json;}

    public void jsonParsing(ArrayList<MySpon> mySponList) {
        if(json == null) return;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray mySponArray = jsonObject.getJSONArray("my_sponlist");
            for(int i=0; i<mySponArray.length(); i++) {
                JSONObject mySponObject = mySponArray.getJSONObject(i);
                MySpon spon = new MySpon();

                spon.setInstitution(mySponObject.getString("institution"));
                spon.setDonation(Integer.parseInt(mySponObject.getString("donation")));
                spon.setDate(mySponObject.getString("date"));
                mySponList.add(spon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
