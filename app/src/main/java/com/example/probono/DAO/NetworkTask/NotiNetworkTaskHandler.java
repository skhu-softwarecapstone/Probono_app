package com.example.probono.DAO.NetworkTask;

import android.content.ContentValues;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DAO.JsonParsing.NoticeJsonParsing;
import com.example.probono.DTO.Notice;
import com.example.probono.MainActivity;
import com.example.probono.DTO.Movie;
import com.example.probono.DAO.JsonParsing.MovieJsonParsing;
import com.example.probono.DAO.NetworkTask.NetworkTask;
import com.example.probono.DAO.RequestHttpURLConnection;
import com.example.probono.NotilistPageFragment_components.RecyclerView_SimpleTextAdapter;

import java.util.ArrayList;

public class NotiNetworkTaskHandler implements NetworkTask.NetworkCallee {

    private String url;
    private ContentValues values;
    private NoticeJsonParsing noticeJsonParsing;
    private RecyclerView recyclerView;
    private ArrayList<Notice> noticeList;
    private MainActivity activity;
    private View view;

    public NotiNetworkTaskHandler(String url, ContentValues values, RecyclerView recyclerView, View view) {
        this.url = url;
        this.values = values;
        this.recyclerView = recyclerView;
        this.noticeList = new ArrayList<>();
        this.view = view;
    }

    @Override
    public String doinBackground() {
        String result = null;
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url, values); //해당 URL로부터 결과물을 얻어온다.
        System.out.println(url);
        return result;
    }

    @Override
    public void onPostExecute(String s) {
        noticeJsonParsing = new NoticeJsonParsing(s);
        noticeJsonParsing.jsonParsing(noticeList);
        //RecyclerView에 Adpater 지정
        RecyclerView_SimpleTextAdapter adapter = new RecyclerView_SimpleTextAdapter(noticeList, view);
        recyclerView.setAdapter(adapter);
    }
}
