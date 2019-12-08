package com.example.probono.SponPageFragment_components;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DAO.JsonParsing.JsonParser;
import com.example.probono.DAO.JsonParsing.SponListParsing;
import com.example.probono.DAO.NetworkTask.JsonNetworkTask;
import com.example.probono.DAO.NetworkTask.NetworkTask;
import com.example.probono.DTO.Spon;
import com.example.probono.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment {

    // 받아올 image Resource id
    private String url;
    private RecyclerView recyclerView;

    View view;
    // url 초기화
    public RecyclerViewFragment(String url) {this.url = url;}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.spon_page_fragment_recyclerviewpager, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.spon_recyclerView);



        new Task(url, null, new SponListParsing()).execute();


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    class Task extends JsonNetworkTask<ArrayList<Spon>>{

        public Task(String url, JSONObject param, JsonParser<ArrayList<Spon>> parser) {
            super(url, param, parser);
        }

        @Override
        public void postTask(ArrayList<Spon> result) {
            RecyclerView_SimpleTextAdapter adapter = new RecyclerView_SimpleTextAdapter(result, view);
            recyclerView.setAdapter(adapter);
        }
    }

}