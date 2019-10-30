package com.example.probono.NotilistPageFragment_components;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class NotilistViewPager_SimpleTextAdapter extends FragmentStatePagerAdapter {
    private int pageCount;
    private String url = "http://58.150.133.213:3000/process/selectnotice";

    public NotilistViewPager_SimpleTextAdapter(@NonNull FragmentManager fm, int pageCount) {
        super(fm);
        this.pageCount = pageCount;
    }


    @Override
    public Fragment getItem(int position) {
        String currentUrl = url +"?index=" + (position);
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

