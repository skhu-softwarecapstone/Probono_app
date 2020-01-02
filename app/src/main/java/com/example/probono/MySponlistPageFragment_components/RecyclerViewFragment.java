package com.example.probono.MySponlistPageFragment_components;

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

import com.example.probono.DAO.NetworkTask.MySponNetworkTaskHandler;
import com.example.probono.DAO.NetworkTask.NetworkTask;
import com.example.probono.MainActivity;
import com.example.probono.R;

public class RecyclerViewFragment extends Fragment {

    // 받아올 image Resource id
    private String url;
    private RecyclerView recyclerView;
    private MainActivity activity;
    private String userid;



    // url 초기화
    public RecyclerViewFragment(String url) {this.url = url;}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_sponlist_recyclerviewpager, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.my_sponlist_recyclerView);

        System.out.println(url);
        MySponNetworkTaskHandler networkTaskHandler = new MySponNetworkTaskHandler(url, null, recyclerView, view);
        NetworkTask networkTask = new NetworkTask(networkTaskHandler);
        networkTask.execute();

        recyclerView.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
        return view;
    }



}