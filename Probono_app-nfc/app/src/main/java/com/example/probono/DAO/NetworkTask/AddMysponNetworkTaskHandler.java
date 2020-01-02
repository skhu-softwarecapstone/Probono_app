package com.example.probono.DAO.NetworkTask;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.probono.DAO.JsonParsing.DialogJsonParsing;
import com.example.probono.DAO.RequestHttpURLConnection;
import com.example.probono.R;

public class AddMysponNetworkTaskHandler extends AsyncTask<Void, Void, String> {

    View view;
    private String url;
    public AddMysponNetworkTaskHandler(String url, View view) {
        this.view = view;
        this.url = url;
    }
    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url, null); //해당 URL로부터 결과물을 얻어온다.
        System.out.println(result);
        return result;
    }

    @Override
    protected void onPostExecute(String dialogData) {
        super.onPostExecute(dialogData);
        if(dialogData!=null) {
            Toast.makeText( view.getContext(), "성공!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText( view.getContext(), "실패!", Toast.LENGTH_LONG).show();
        }
    }
}
