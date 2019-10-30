package com.example.probono.DAO.NetworkTask;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.probono.DAO.JsonParsing.DialogJsonParsing;
import com.example.probono.DAO.RequestHttpURLConnection;
import com.example.probono.DailylogPageFragment.DialogData;
import com.example.probono.R;

public class DialContentNetworkTaskHandler extends AsyncTask<Void, Void, String> {
    private String url = "http://58.150.133.213:3000/process/select_dailylog";
    private String userid;
    private String date;
    private View view;

    DialogData dialogData;


    public DialContentNetworkTaskHandler(String userid, String date, View view){
        this.userid=userid;
        this.date =date;
        this.view = view;
        this.dialogData = new DialogData();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url+"?id="+userid+"&date="+date, null); //해당 URL로부터 결과물을 얻어온다.
        System.out.println(url);

        return result;
    }

    @Override
    protected void onPostExecute(String dialogData) {
        super.onPostExecute(dialogData);
        Log.d("dd", dialogData);
        new DialogJsonParsing(dialogData).jsonParsing(this.dialogData);

        TextView dialogTitle  = ((TextView)view.findViewById(R.id.prevDialTitle));
        TextView dialogContent = ((TextView)view.findViewById(R.id.prevDialContent));
        dialogTitle.setText(this.dialogData.getTitle());
        dialogContent.setText(this.dialogData.getContent());


    }
}
