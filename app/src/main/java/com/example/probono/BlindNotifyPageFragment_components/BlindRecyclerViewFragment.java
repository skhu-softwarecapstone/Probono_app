package com.example.probono.BlindNotifyPageFragment_components;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DAO.JsonParsing.BlindListJsonParsing;
import com.example.probono.DAO.JsonParsing.JsonParser;
import com.example.probono.DAO.JsonParsing.NotiListJsonParsing;
import com.example.probono.DAO.NetworkTask.JsonNetworkTask;
import com.example.probono.DTO.Blind;
import com.example.probono.DTO.Notice;
import com.example.probono.R;

import org.json.JSONObject;

import java.util.ArrayList;

public class BlindRecyclerViewFragment extends Fragment {


    private String url;
    private RecyclerView recyclerView;
    Context context;

    View view;
    // url 초기화

    public BlindRecyclerViewFragment(String url, Context context) {
        this.url = url;
        this.context = context;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.blindlist_page_fragment_recyclerviewpager, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.blind_recyclerView);

        new Task(url, null, new BlindListJsonParsing()).execute();


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    class Task extends JsonNetworkTask<ArrayList<Blind>>{

        public Task(String url, JSONObject param, JsonParser<ArrayList<Blind>> parser) {
            super(url, param, parser);
        }

        @Override
        public void postTask(ArrayList<Blind> result) {
            Blind_RecyclerView_SimpleTextAdapter adapter = new Blind_RecyclerView_SimpleTextAdapter(result, view, context);
            recyclerView.setAdapter(adapter);
        }
    }


}