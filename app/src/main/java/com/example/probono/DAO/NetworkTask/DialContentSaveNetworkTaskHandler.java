package com.example.probono.DAO.NetworkTask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.probono.DAO.RequestHttpURLConnection;
import com.example.probono.DailylogPageFragment.DialogData;

import java.net.URLDecoder;
import java.net.URLEncoder;


public class DialContentSaveNetworkTaskHandler extends AsyncTask<Void, Void, Void> {
    private String url ="http://58.150.133.213:3000/process/add_dailylog";
    private DialogData dialogData;
    public DialContentSaveNetworkTaskHandler(DialogData dialogData){
        this.dialogData = dialogData;
    }
    @Override
    protected Void doInBackground(Void... voids) {

        String requestUrl = url+"?id="+dialogData.getUser()+"&date="+dialogData.getDate()+"&content="+dialogData.getContent()+"&title="+dialogData.getTitle();

        try {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            requestHttpURLConnection.request(requestUrl,  null); //해당 URL로부터 결과물을 얻어온다.
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("save data",requestUrl);
        return null;
    }
}
