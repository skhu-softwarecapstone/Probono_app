package com.example.probono.DAO.JsonParsing;

import com.example.probono.DTO.Movie;
import com.example.probono.DTO.Notice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoticeJsonParsing {
    private String json;

    public NoticeJsonParsing(String json) {this.json = json;}

    public void jsonParsing(ArrayList<Notice> noticeList) {
        if(json == null) return;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray noticeArray = new JSONArray(jsonObject);
            for(int i=0; i<noticeArray.length(); i++) {
                JSONObject noticeObject = noticeArray.getJSONObject(i);
                Notice notice = new Notice();

                notice.setTitle(noticeObject.getString("title"));
                notice.setDate(noticeObject.getString("createDate"));
                notice.setContent(noticeObject.getString("content"));
                notice.setNo(noticeObject.getString("no"));
                notice.setUserId(noticeObject.getString("userId"));

                noticeList.add(notice);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
