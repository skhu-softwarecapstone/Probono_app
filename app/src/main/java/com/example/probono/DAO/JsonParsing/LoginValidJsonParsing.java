package com.example.probono.DAO.JsonParsing;

import com.example.probono.DailylogPageFragment.DialogData;
import com.example.probono.DailylogPageFragment.UserInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginValidJsonParsing {

    String data;

    public LoginValidJsonParsing(String data){
        this.data = data;
    }

    public void jsonParsing(UserInfo userInfo) {
        if(data == null) return;
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject loginData = jsonObject.getJSONObject("login");


            userInfo.setUserId(loginData.getString("id"));
            userInfo.setLoginSuccess(Boolean.parseBoolean(loginData.getString("success")));
            userInfo.setRole(loginData.getString("role"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
