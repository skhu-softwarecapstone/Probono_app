package com.example.probono.DAO.NetworkTask;

import android.content.ContentValues;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DAO.JsonParsing.SponJsonParsing;
import com.example.probono.DTO.Spon;
import com.example.probono.MainActivity;
import com.example.probono.DTO.Movie;
import com.example.probono.DAO.JsonParsing.MovieJsonParsing;
import com.example.probono.DAO.NetworkTask.NetworkTask;
import com.example.probono.DAO.RequestHttpURLConnection;
import com.example.probono.SponPageFragment_components.RecyclerView_SimpleTextAdapter;

import java.util.ArrayList;

public class SponNetworkTaskHandler implements NetworkTask.NetworkCallee {

    private String url;
    private ContentValues values;
    private SponJsonParsing movieJsonParsing;
    private RecyclerView recyclerView;
    private ArrayList<Spon> sponList;
    private MainActivity activity;
    private View view;
    public SponNetworkTaskHandler(String url, ContentValues values, RecyclerView recyclerView, View view) {
        this.url = url;
        this.values = values;
        this.recyclerView = recyclerView;
        this.sponList = new ArrayList<>();
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
        movieJsonParsing = new SponJsonParsing(s);
        movieJsonParsing.jsonParsing(sponList);
        //RecyclerView에 Adpater 지정
        RecyclerView_SimpleTextAdapter adapter = new RecyclerView_SimpleTextAdapter(sponList, view);
        recyclerView.setAdapter(adapter);
    }
}
