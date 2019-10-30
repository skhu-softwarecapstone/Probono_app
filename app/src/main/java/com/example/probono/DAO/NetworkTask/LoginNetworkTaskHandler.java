package com.example.probono.DAO.NetworkTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.probono.DAO.JsonParsing.LoginValidJsonParsing;
import com.example.probono.DAO.RequestHttpURLConnection;
import com.example.probono.DailylogPageFragment.UserInfo;
import com.example.probono.LoginActivity;
import com.example.probono.MainActivity;

public class LoginNetworkTaskHandler extends AsyncTask<Void, Void, String> {
    String url = "http://58.150.133.213:3000/process/login";
    Activity loginActivity;
    UserInfo userInfo;
    ProgressBar loadingProgressBar;

    public LoginNetworkTaskHandler(Activity loginActivity, UserInfo userinfo, ProgressBar loadingProgressBar){
        this.loginActivity = loginActivity;
        this.userInfo = userinfo;
        this.loadingProgressBar = loadingProgressBar;

    }


    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url+"?id="+userInfo.getUserId()+"&password="+userInfo.getPassword(), null); //해당 URL로부터 결과물을 얻어온다.
        System.out.println(url);
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        new LoginValidJsonParsing(result).jsonParsing(this.userInfo);

        if(userInfo.getLoginSuccess()){
            Intent intent = new Intent(loginActivity.getApplicationContext(), MainActivity.class);
            intent.putExtra("userId", userInfo.getUserId());
            intent.putExtra("userRole", userInfo.getRole());
            loginActivity.startActivityForResult(intent , 201);
        }else{
            loadingProgressBar.setVisibility(View.GONE);
            Toast.makeText(loginActivity.getApplicationContext(), "로그인 실패! 정보를 확인해 주세요", Toast.LENGTH_SHORT).show();

        }



    }
}
