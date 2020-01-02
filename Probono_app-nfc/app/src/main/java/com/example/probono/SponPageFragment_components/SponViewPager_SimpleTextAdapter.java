package com.example.probono.SponPageFragment_components;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.probono.staticValue.Sv;

public class SponViewPager_SimpleTextAdapter extends FragmentStatePagerAdapter {
    private int pageCount;
    private String url = Sv.sponContent;

    public SponViewPager_SimpleTextAdapter(@NonNull FragmentManager fm, int pageCount) {
        super(fm);
        this.pageCount = pageCount;
    }


    @Override
    public Fragment getItem(int position) {
        String currentUrl = url  + (position+1);
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

