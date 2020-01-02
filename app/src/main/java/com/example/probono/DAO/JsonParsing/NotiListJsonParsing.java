package com.example.probono.DAO.JsonParsing;

import com.example.probono.DTO.Notice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NotiListJsonParsing implements JsonParser<ArrayList<Notice>> {
    @Override
    public ArrayList<Notice> parse(String json) {
        if(json == null) return null;
        ArrayList<Notice> noticeList = new ArrayList<>();
        try {
            JSONArray noticeArray = new JSONArray(json);
            for(int i=0; i<noticeArray.length(); i++) {
                JSONObject noticeObject = noticeArray.getJSONObject(i);
                Notice notice = new Notice();

                notice.setUserId(noticeObject.getString("userId"));
                notice.setTitle(noticeObject.getString("title"));
                notice.setDate(noticeObject.getString("createdDate"));
                notice.setContent(noticeObject.getString("content"));

                noticeList.add(notice);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return noticeList;
    }
}
