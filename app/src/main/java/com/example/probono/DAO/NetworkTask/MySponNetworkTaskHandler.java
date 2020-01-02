package com.example.probono.DAO.NetworkTask;

import android.content.ContentValues;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DAO.JsonParsing.MySponJsonParsing;
import com.example.probono.DTO.MySpon;
import com.example.probono.MainActivity;
import com.example.probono.DAO.RequestHttpURLConnection;
import com.example.probono.MySponlistPageFragment_components.RecyclerView_SimpleTextAdapter;

import java.util.ArrayList;

public class MySponNetworkTaskHandler implements NetworkTask.NetworkCallee {

    private String url;
    private MySponJsonParsing movieJsonParsing;
    private RecyclerView recyclerView;
    private ArrayList<MySpon> sponList;
    private View view;
    public MySponNetworkTaskHandler(String url, ContentValues values, RecyclerView recyclerView, View view) {
        this.url = url;
        this.recyclerView = recyclerView;
        this.sponList = new ArrayList<>();
        this.view = view;
    }

    @Override
    public String doinBackground() {
        String result = null;
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url, null); //해당 URL로부터 결과물을 얻어온다.
        return result;
    }

    @Override
    public void onPostExecute(String s) {
        movieJsonParsing = new MySponJsonParsing(s);
        movieJsonParsing.jsonParsing(sponList);
        //RecyclerView에 Adpater 지정

        RecyclerView_SimpleTextAdapter adapter = new RecyclerView_SimpleTextAdapter(sponList, view);
        recyclerView.setAdapter(adapter);
    }
}
