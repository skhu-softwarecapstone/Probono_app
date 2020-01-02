package com.example.probono.DAO.JsonParsing;

import com.example.probono.DTO.Blind;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;


import java.util.Date;
import java.text.SimpleDateFormat;

public class BlindListJsonParsing implements JsonParser<ArrayList<Blind>> {
    @Override
    public ArrayList<Blind> parse(String json) {
        if(json==null)return null;
        ArrayList<Blind> blindList = new ArrayList<>();



        try {
            JSONArray noticeArray = new JSONArray(json);
            for(int i=0; i<noticeArray.length(); i++) {
                JSONObject noticeObject = noticeArray.getJSONObject(i);
                Blind notice = new Blind();

                notice.setUserId(noticeObject.getString("userId"));
                notice.setName(noticeObject.getString("name"));
                notice.setDate(noticeObject.getString("date"));
                notice.setContent(noticeObject.getString("content"));
                notice.setbNo(noticeObject.getInt("bNo"));

                blindList.add(notice);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return blindList;
    }
}
