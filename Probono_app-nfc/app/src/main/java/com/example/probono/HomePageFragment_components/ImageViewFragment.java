package com.example.probono.HomePageFragment_components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.probono.R;

public class ImageViewFragment extends Fragment {

    // 받아올 image Resource id
    private int imageId;
    // constructor 매개변수로 imageId초기화
    public ImageViewFragment(int imageId) {this.imageId = imageId;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page_fragment_imageview, container, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.home_image_view);
        imageView.setImageResource(imageId);
        return view;

    }
}
