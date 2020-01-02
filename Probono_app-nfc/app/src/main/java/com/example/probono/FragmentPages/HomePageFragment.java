package com.example.probono.FragmentPages;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.probono.DAO.JsonParsing.JsonParser;
import com.example.probono.DAO.JsonParsing.NotiListJsonParsing;
import com.example.probono.DAO.JsonParsing.SponListParsing;
import com.example.probono.DAO.NetworkTask.JsonNetworkTask;
import com.example.probono.DTO.Notice;
import com.example.probono.DTO.Spon;
import com.example.probono.HomePageFragment_components.SliderImagesPagerAdapter;
import com.example.probono.MainActivity;
import com.example.probono.DAO.NetworkTask.NetworkTask;
import com.example.probono.R;
import com.example.probono.staticValue.Sv;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomePageFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Button onOff_Button;
    private RecyclerView notiRecyclerView;
    private RecyclerView sponRecyclerView;
    private SliderImagesPagerAdapter imagesPagerAdapter;
    private MainActivity activity;
    View view;

    //slider On/Off 플래그
    private boolean sliderFlag = true;
    //slider On/Off 판별 플래그
    private boolean sliderStateFlag = false;
    //현재 실행 중인 current page
    private int currentPage = 0;

    public  synchronized  boolean getSliderFlag() {
        return this.sliderFlag;
    }

    public synchronized int getCurrentPage() {
        return currentPage;
    }

    public synchronized void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

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
        view = inflater.inflate(R.layout.home_page_fragment, null);
        intializeTabLayout(view);
        initializeButton(view);
        initializeViewPager(view);
        initializeRecyclerView(view);
        setTabLayoutEventHandler();
        setButtonEventHandler();
        imageAutoSliderTask();
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sliderFlag = false;
    }

    //tablayout 초기화 및 tab 추가
    public void intializeTabLayout(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.banner_tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab().setText(""));
        tabLayout.addTab(tabLayout.newTab().setText(""));
    }

    //button 초기화
    public void initializeButton(View view) {
        onOff_Button = (Button) view.findViewById(R.id.autoSlide_Button);

    }

    //viewPager 초기화
    public void initializeViewPager(View item) {
        viewPager = (ViewPager) item.findViewById(R.id.banner_viewPager);
        imagesPagerAdapter = new SliderImagesPagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(imagesPagerAdapter);

        //ViewPager 이벤트핸들러를 tabLayout이벤트 핸들러로 등록
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    //recyclerView 초기화
    private void initializeRecyclerView(View view) {
        //리사이클러뷰에 표시할 데이터 리스트 생성.
        String url = Sv.noticeContent+"1";
        String url2 = Sv.sponContent+"1";

        notiRecyclerView = (RecyclerView)view.findViewById(R.id.home_noti_recyclerView);
        sponRecyclerView = (RecyclerView)view.findViewById(R.id.home_spon_recyclerView);

        new notiTask(url, null, new NotiListJsonParsing()).execute();
        new SponTask(url2, null, new SponListParsing()).execute();


        //RecyclerView에 LinearLayoutManager 객체 지정
        notiRecyclerView.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
        sponRecyclerView.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
    }
    class notiTask extends JsonNetworkTask<ArrayList<Notice>>{
        public notiTask(String url, JSONObject param, JsonParser<ArrayList<Notice>> parser) {
            super(url, param, parser);
        }
        @Override
        public void postTask(ArrayList<Notice> result) {
            com.example.probono.NotilistPageFragment_components.RecyclerView_SimpleTextAdapter adapter =
                    new com.example.probono.NotilistPageFragment_components.RecyclerView_SimpleTextAdapter(result, view);
            notiRecyclerView.setAdapter(adapter);
        }
    }

    class SponTask extends JsonNetworkTask<ArrayList<Spon>>{

        public SponTask(String url, JSONObject param, JsonParser<ArrayList<Spon>> parser) {
            super(url, param, parser);
        }

        @Override
        public void postTask(ArrayList<Spon> result) {
            com.example.probono.SponPageFragment_components.RecyclerView_SimpleTextAdapter adapter =
                    new com.example.probono.SponPageFragment_components.RecyclerView_SimpleTextAdapter(result, view);
            sponRecyclerView.setAdapter(adapter);
        }
    }

    //tabLayout 이벤트 설정 - viewPager 연동
    public void setTabLayoutEventHandler() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentPage(tab.getPosition());
                viewPager.setCurrentItem(getCurrentPage());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //Button EventHandler 설정
    public void setButtonEventHandler() {
        onOff_Button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onOff 기능을 위한 SliderFlag 반전
                sliderFlag = !sliderFlag;
                if(sliderFlag && !sliderStateFlag)
                    imageAutoSliderTask(); // sliderState 플래그가 false면 동작
            }
        });
    }

    // image autoSlide 동작
    public void imageAutoSliderTask() {
        Thread thread = new Thread(){
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(5000);
                        if(!getSliderFlag()) break;
                        int position = getCurrentPage()+1;
                        if(position>2) position=0;
                        activity.runOnUiThread(new SlideThread(position));
                        System.out.println(position);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    public class SlideThread extends Thread {
        private int position;
        public SlideThread(int position) {
            this.position = position;
        }
        public void run() {
            viewPager.setCurrentItem(position);
        }
    }

}

