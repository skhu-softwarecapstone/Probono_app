package com.example.probono.FragmentPages;

import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.probono.BlindNotifyPageFragment_components.BlindContentDialog;
import com.example.probono.BlindNotifyPageFragment_components.BlindListViewPager_SimpleTextAdapter;
import com.example.probono.DAO.JsonParsing.CountParsing;
import com.example.probono.DAO.JsonParsing.JsonParser;
import com.example.probono.DAO.NetworkTask.JsonNetworkTask;
import com.example.probono.DTO.Blind;
import com.example.probono.R;
import com.example.probono.staticValue.Sv;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

public class BlindPageFragment extends Fragment {
    View view;

    private ViewPager viewPager;
    private RecyclerView blindRecyclerView;
    private BlindListViewPager_SimpleTextAdapter viewPagerAdapter;

    private TabLayout tabLayout;
    private int pageItems = 10;
    private int pageCount = 0;
    public static JsonNetworkTask<Integer> blindTask;
    private Button insert;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.blind_fragment, null);



        tabLayout = (TabLayout) view.findViewById(R.id.blind_tabLayout);
        blindTask= new Task(Sv.blindCount, null, new CountParsing());
        blindTask.execute();

        insert = view.findViewById(R.id.inputBlind);
        insert.setOnClickListener(v->{
            BlindContentDialog dialog = new BlindContentDialog(getContext(), true);
            dialog.show();

        });

        return view;
    }

    public void initializeViewPager(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.blind_viewPager);
        viewPagerAdapter = new BlindListViewPager_SimpleTextAdapter(getFragmentManager(), tabLayout.getTabCount(), getContext());
        viewPager.setAdapter(viewPagerAdapter);




        //ViewPager 이벤트핸들러를 tabLayout이벤트 핸들러로 등록
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


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

    public synchronized TabLayout getTabLayout() {
        return tabLayout;
    }

    public class Task extends JsonNetworkTask<Integer>{

        public Task(String url, JSONObject param, JsonParser<Integer> parser) {
            super(url, param, parser);
        }

        @Override
        public void postTask(Integer itemCount) {
            int a=0;
            if(itemCount == null) {
                itemCount = 0;
            }else {
                a = itemCount / pageItems;
            }
            pageCount = itemCount%pageItems==0?a:a+1;

            getTabLayout().removeAllTabs();

            for(int i=1; i<=pageCount; i++) {
                getTabLayout().addTab(tabLayout.newTab().setText(""+i));
            }
            initializeViewPager(view);
            setTabLayoutEventHandler();

            blindTask= new Task(Sv.blindCount, null, new CountParsing());
        }
    }

}
