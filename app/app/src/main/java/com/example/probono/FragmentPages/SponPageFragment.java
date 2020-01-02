package com.example.probono.FragmentPages;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.probono.DAO.JsonParsing.CountParsing;
import com.example.probono.DAO.JsonParsing.JsonParser;
import com.example.probono.DAO.NetworkTask.JsonNetworkTask;
import com.example.probono.R;
import com.example.probono.SponPageFragment_components.SponViewPager_SimpleTextAdapter;
import com.example.probono.staticValue.Sv;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

public class SponPageFragment extends Fragment {

    View view;
    private ViewPager viewPager;
    private SponViewPager_SimpleTextAdapter viewPagerAdapter;

    public synchronized TabLayout getTabLayout() {
        return tabLayout;
    }

    private TabLayout tabLayout;
    private int pageItems = 10;
    private int pageCount = 0;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.spon_page_fragment, null);

        String url = Sv.sponCount;

        tabLayout = (TabLayout) view.findViewById(R.id.spon_tabLayout);


        new Task(url, null, new CountParsing()).execute();


        return view;
    }


    //viewPager 초기화
    public void initializeViewPager(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.spon_viewPager);
        viewPagerAdapter = new SponViewPager_SimpleTextAdapter(getFragmentManager(), tabLayout.getTabCount());
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


    public class Task extends JsonNetworkTask<Integer> {

        public Task(String url, JSONObject param, JsonParser<Integer> parser) {
            super(url, param, parser);
        }

        @Override
        public void postTask(Integer itemCount) {
            int a = itemCount/pageItems;
            pageCount = itemCount%pageItems==0?a:a+1;

            for(int i=1; i<=pageCount; i++) {
                getTabLayout().addTab(tabLayout.newTab().setText(""+i));
            }
            initializeViewPager(view);
            setTabLayoutEventHandler();
        }
    }
}