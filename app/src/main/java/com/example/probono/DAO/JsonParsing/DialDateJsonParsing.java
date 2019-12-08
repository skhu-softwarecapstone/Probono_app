package com.example.probono.DAO.JsonParsing;

import com.example.probono.DTO.Notice;
import com.example.probono.DailylogPageFragment.DialogData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DialDateJsonParsing implements JsonParser<ArrayList<Date>> {

    @Override
    public ArrayList<Date> parse(String jsonData) {
        ArrayList<Date> dialDateList = new ArrayList();

        //SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        //dataFormat.parse(dialDateObject.getString("date"))
        try {
            //JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray dialogDates = new JSONArray(jsonData);


            for(int i=0; i<dialogDates.length(); i++) {
                JSONObject dialDateObject = dialogDates.getJSONObject(i);
                dialDateList.add(new Date(dialDateObject.getString("date")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dialDateList;
    }
}
