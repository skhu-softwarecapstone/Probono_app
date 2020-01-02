package com.example.probono.DAO.JsonParsing;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class VisitDateJsonParsing implements JsonParser<ArrayList<Date>> {

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
                dialDateList.add(new Date(dialDateObject.getString("visitDate")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dialDateList;
    }
}
