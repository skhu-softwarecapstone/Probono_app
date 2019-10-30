package com.example.probono.DAO.JsonParsing;

import com.example.probono.DailylogPageFragment.DialogData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DialogJsonParsing {
    String data;

    public DialogJsonParsing(String data){
        this.data = data;
    }
    public void jsonParsing(DialogData dialogData) {
        if(data == null) return;
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray dialogDates = jsonObject.getJSONArray("dailylog");
            for(int i=0; i<dialogDates.length(); i++) {
                JSONObject dialDateObject = dialogDates.getJSONObject(i);

                dialogData.setTitle(dialDateObject.getString("title"));
                dialogData.setDate(dialDateObject.getString("date"));
                dialogData.setContent(dialDateObject.getString("content"));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
