package com.example.probono.DAO.JsonParsing;

import com.example.probono.DTO.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginValidJsonParsing {

    String data;

    public LoginValidJsonParsing(String data){
        this.data = data;
    }

    public void jsonParsing(UserInfo userInfo) {
        if(data == null) return;
        System.out.println(data);
        try {

            JSONObject jsonObject = new JSONObject(data);
            //JSONObject loginData = jsonObject.getJSONObject("");


            userInfo.setUserId(jsonObject.getString("userEmail"));
            userInfo.setLoginSuccess(jsonObject.getString("sucessMessage"));
            userInfo.setLoginFail(jsonObject.getString("errorMessage"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
