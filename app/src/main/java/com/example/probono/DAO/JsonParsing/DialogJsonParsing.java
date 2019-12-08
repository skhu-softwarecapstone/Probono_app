package com.example.probono.DAO.JsonParsing;

import com.example.probono.DailylogPageFragment.DialogData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DialogJsonParsing implements JsonParser<DialogData>{

    @Override
    public DialogData parse(String data) {
        if(data == null) return null;

        DialogData dialogData = new DialogData( );
        try {
            JSONObject jsonObject = new JSONObject(data);
            //JSONArray dialogDates = jsonObject.getJSONArray("dailylog");
            //for(int i=0; i<dialogDates.length(); i++) {
                //SONObject dialDateObject = dialogDates.getJSONObject(i);

                dialogData.setTitle(jsonObject.getString("seniorName"));
                dialogData.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date(jsonObject.getString("date"))));
                dialogData.setContent(jsonObject.getString("content"));
                dialogData.setdNo(jsonObject.getInt("dNo"));
            //}

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return dialogData;
    }
}
