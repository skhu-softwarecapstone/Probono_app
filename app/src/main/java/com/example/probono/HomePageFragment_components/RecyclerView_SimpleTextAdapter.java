package com.example.probono.HomePageFragment_components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.MainActivity;
import com.example.probono.DTO.Movie;
import com.example.probono.R;

import java.util.ArrayList;

public class RecyclerView_SimpleTextAdapter extends RecyclerView.Adapter<RecyclerView_SimpleTextAdapter.ViewHolder> {
    private ArrayList<Movie> mDataList = null;
    private MainActivity activity = null;

    //생성자에서 데이터 리스트 객체를 전달
    public RecyclerView_SimpleTextAdapter(ArrayList<Movie> list, MainActivity activity) {
        mDataList = list;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 개체에 대한 참조
            textView = itemView.findViewById(R.id.home_recycler_text);
        }
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view  = inflater.inflate(R.layout.home_page_fragment_recycleview, parent, false );
        RecyclerView_SimpleTextAdapter.ViewHolder viewHolder = new RecyclerView_SimpleTextAdapter.ViewHolder(view);
        return viewHolder;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull RecyclerView_SimpleTextAdapter.ViewHolder viewHolder, int position) {
        String text = mDataList.get(position).getTitle();
        viewHolder.textView.setText(text);
    }

    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}
