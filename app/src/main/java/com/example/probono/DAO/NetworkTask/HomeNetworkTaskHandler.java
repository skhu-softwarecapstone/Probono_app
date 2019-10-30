package com.example.probono.DAO.NetworkTask;

import android.content.ContentValues;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DAO.JsonParsing.NoticeJsonParsing;
import com.example.probono.DAO.JsonParsing.SponJsonParsing;
import com.example.probono.DTO.Notice;
import com.example.probono.DTO.Spon;
import com.example.probono.HomePageFragment_components.RecyclerView_SimpleTextAdapter;
import com.example.probono.MainActivity;
import com.example.probono.DTO.Movie;
import com.example.probono.DAO.JsonParsing.MovieJsonParsing;
import com.example.probono.DAO.NetworkTask.NetworkTask;
import com.example.probono.DAO.RequestHttpURLConnection;

import java.util.ArrayList;

public class HomeNetworkTaskHandler implements NetworkTask.NetworkCallee {

    private String url;
    private ContentValues values;
    private RecyclerView recyclerView;
    private MainActivity activity;
    private View view;

    public HomeNetworkTaskHandler(String url, ContentValues values, RecyclerView recyclerView, View view) {
        this.url = url;
        this.values = values;
        this.recyclerView = recyclerView;
        this.activity = activity;
        this.view = view;
    }

    @Override
    public String doinBackground() {
        String result = null;
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url, values); //해당 URL로부터 결과물을 얻어온다.
        return result;
    }

    @Override
    public void onPostExecute(String s) {
        //RecyclerView에 Adpater 지정

        if(url=="http://58.150.133.213:3000/process/selectnotice?index=0") {
            ArrayList<Notice> list = new ArrayList<>();
            new NoticeJsonParsing(s).jsonParsing(list);
            //RecyclerView에 Adpater 지정
            com.example.probono.NotilistPageFragment_components.RecyclerView_SimpleTextAdapter adapter =
                    new com.example.probono.NotilistPageFragment_components.RecyclerView_SimpleTextAdapter(list, view);
            recyclerView.setAdapter(adapter);
        }
        if(url=="http://58.150.133.213:3000/process/selectspon?index=0") {
            ArrayList<Spon> list = new ArrayList<>();
            new SponJsonParsing(s).jsonParsing(list);
            //RecyclerView에 Adpater 지정
            com.example.probono.SponPageFragment_components.RecyclerView_SimpleTextAdapter adapter =
                    new com.example.probono.SponPageFragment_components.RecyclerView_SimpleTextAdapter(list, view);
            recyclerView.setAdapter(adapter);
        }
    }
}
