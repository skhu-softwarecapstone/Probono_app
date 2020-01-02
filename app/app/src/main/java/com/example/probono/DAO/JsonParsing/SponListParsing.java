package com.example.probono.DAO.JsonParsing;

import com.example.probono.DTO.Spon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SponListParsing implements JsonParser<ArrayList<Spon>> {
    @Override
    public ArrayList<Spon> parse(String json) {

        ArrayList<Spon> sponList = new ArrayList<>();
        try {
            JSONArray sponArray = new JSONArray(json);
            for(int i=0; i<sponArray.length(); i++) {
                JSONObject sponObject = sponArray.getJSONObject(i);
                Spon spon = new Spon();


                spon.setTitle(sponObject.getString("name"));
                spon.setContent(sponObject.getString("introduce"));

                JSONObject addrObject = sponObject.getJSONObject("address");
                spon.setAddress(addrObject.getString("address1")+" "+addrObject.getString("address_detail"));



                sponList.add(spon);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sponList;
    }
}
