package com.example.probono.HomePageFragment_components;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.probono.R;

public class SliderImagesPagerAdapter extends FragmentStatePagerAdapter {
    private int pageCount;
    public SliderImagesPagerAdapter(@NonNull FragmentManager fm, int pageCount) {
        super(fm);
        this.pageCount = pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            {
                ImageViewFragment fragment_imageView = new ImageViewFragment(R.drawable.img01);
                return fragment_imageView;
            }
            case 1:
            {
                ImageViewFragment fragment_imageView = new ImageViewFragment(R.drawable.img02);
                return fragment_imageView;
            }
            case 2:
            {
                ImageViewFragment fragment_imageView = new ImageViewFragment(R.drawable.img03);
                return fragment_imageView;
            }
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return pageCount;
    }
}
