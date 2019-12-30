package com.example.probono.MySponlistPageFragment_components;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.probono.R;

public class MySponlistViewPager_SimpleTextAdapter extends FragmentStatePagerAdapter {
    private int pageCount;
    private String url = R.string.server_ip+"/process/select_mysponlist";
    private String userid ;
    public MySponlistViewPager_SimpleTextAdapter(@NonNull FragmentManager fm, int pageCount, String userid) {
        super(fm);
        this.pageCount = pageCount;
        this.userid = userid;
    }


    @Override
    public Fragment getItem(int position) {
        String currentUrl = url +"?id=" + userid;

        System.out.println("--"+currentUrl);
        RecyclerViewFragment recyclerViewFragment = new RecyclerViewFragment(currentUrl);
        return recyclerViewFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "" + (position + 1);
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}

