package com.example.probono.FragmentPages;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.viewpager.widget.ViewPager;


import com.example.probono.MainActivity;
import com.example.probono.DTO.Movie;
import com.example.probono.MySponlistPageFragment_components.MySponlistViewPager_SimpleTextAdapter;
import com.example.probono.R;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MySponlistPageFragment extends Fragment {

    private ViewPager viewPager;
    private MySponlistViewPager_SimpleTextAdapter viewPagerAdapter;
    private String userid;

    public MySponlistPageFragment(String userid){
        this.userid = userid;
    }

    public synchronized TabLayout getTabLayout() {
        return tabLayout;
    }

    private TabLayout tabLayout;
    private MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        //        //메소드가 호출될 때, 프래그먼트가 액티비티 위에 올라와있다.
        //getActivity Method로 참조가능
        activity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_spnolist_page_fragment, null);
        intializeTabLayout(view);
        initializeViewPager(view);
        setTabLayoutEventHandler();
        return view;
    }

    //tablayout 초기화 및 tab 추가
    public void intializeTabLayout(View view) {
//        String countUrl = "http://58.150.133.213:3000/process/countspon";

        tabLayout = (TabLayout) view.findViewById(R.id.my_sponlist_tabLayout);

//        NetworkTask networkTask = new NetworkTask(countUrl, null, view);
//        networkTask.execute();
        getTabLayout().addTab(tabLayout.newTab().setText("my sponlist"));
    }

    //viewPager 초기화
    public void initializeViewPager(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.my_sponlist_viewPager);
        viewPagerAdapter = new MySponlistViewPager_SimpleTextAdapter(getFragmentManager(), tabLayout.getTabCount(), userid);
        viewPager.setAdapter(viewPagerAdapter);

        //ViewPager 이벤트핸들러를 tabLayout이벤트 핸들러로 등록
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    //tabLayout 이벤트 설정 - viewPager 연동
    public void setTabLayoutEventHandler() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}