package com.example.probono.DAO.NetworkTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.probono.DAO.JsonParsing.LoginValidJsonParsing;
import com.example.probono.DAO.RequestHttpURLConnectionForLogin;
import com.example.probono.DTO.UserInfo;
import com.example.probono.MainActivity;
import com.example.probono.staticValue.Sv;

public class LoginNetworkTaskHandler extends AsyncTask<Void, Void, String> {
    String url ;



    Activity loginActivity;
    UserInfo userInfo;
    ProgressBar loadingProgressBar;

    public LoginNetworkTaskHandler(Activity loginActivity, UserInfo userinfo, ProgressBar loadingProgressBar){
        this.loginActivity = loginActivity;
        this.userInfo = userinfo;
        this.loadingProgressBar = loadingProgressBar;
        url = Sv.loginRequest;

    }


    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        RequestHttpURLConnectionForLogin requestHttpURLConnection = new RequestHttpURLConnectionForLogin();
        result = requestHttpURLConnection.request(url, "email="+userInfo.getUserId()+"&password="+userInfo.getPassword()+"&mobile=true"); //해당 URL로부터 결과물을 얻어온다.
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        new LoginValidJsonParsing(result).jsonParsing(this.userInfo);

        if(userInfo.getLoginSuccess()!=null&&userInfo.getLoginSuccess().equals("SUCC")&&CookieManager.getInstance().getCookie("sessionId")!=null&&!CookieManager.getInstance().getCookie("sessionId").isEmpty()){
            Intent intent = new Intent(loginActivity.getApplicationContext(), MainActivity.class);
            intent.putExtra("userId", userInfo.getUserId());
            //Toast.makeText(loginActivity, "쿠키 데이터 "+ CookieManager.getInstance().getCookie("sessionId"), Toast.LENGTH_LONG).show();
            loadingProgressBar.setVisibility(View.GONE);
            loginActivity.startActivityForResult(intent , 201);
        }else{
            loadingProgressBar.setVisibility(View.GONE);
            Toast.makeText(loginActivity.getApplicationContext(), "로그인 실패! 정보를 확인해 주세요"+userInfo.getLoginFail(), Toast.LENGTH_SHORT).show();

        }


    }
}
