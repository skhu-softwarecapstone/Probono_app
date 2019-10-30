package com.example.probono.DAO.JsonParsing;

import com.example.probono.DTO.Notice;
import com.example.probono.DailylogPageFragment.DialogData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DialDateJsonParsing {
    private String jsonData;
    public DialDateJsonParsing(String data){
        this.jsonData = data;
    }

    public void jsonParsing(ArrayList<Date> dialDateList) {
        if(jsonData == null) return;

        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray dialogDates = jsonObject.getJSONArray("dates");
            for(int i=0; i<dialogDates.length(); i++) {
                JSONObject dialDateObject = dialogDates.getJSONObject(i);
                dialDateList.add(dataFormat.parse(dialDateObject.getString("date")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch(ParseException pe){
            pe.printStackTrace();
        }
    }
}
