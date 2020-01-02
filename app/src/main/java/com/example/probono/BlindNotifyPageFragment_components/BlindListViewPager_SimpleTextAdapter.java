package com.example.probono.BlindNotifyPageFragment_components;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.probono.staticValue.Sv;

public class BlindListViewPager_SimpleTextAdapter extends FragmentStatePagerAdapter {
    private int pageCount;
    private String url = Sv.blindContent;
    private Context context;

    public BlindListViewPager_SimpleTextAdapter(@NonNull FragmentManager fm, int pageCount, Context context) {
        super(fm);
        this.pageCount = pageCount;
        this.context = context;

    }


    @Override
    public Fragment getItem(int position) {
        String currentUrl = url  + (position+1);
        BlindRecyclerViewFragment recyclerViewFragment = new BlindRecyclerViewFragment(currentUrl, context);
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

