package com.example.probono.MySponlistPageFragment_components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DTO.MySpon;
import com.example.probono.MainActivity;
import com.example.probono.R;

import java.util.ArrayList;

public class RecyclerView_SimpleTextAdapter extends RecyclerView.Adapter<RecyclerView_SimpleTextAdapter.ViewHolder> {
    private ArrayList<MySpon> mDataList = null;
    private MainActivity activity = null;
    private  View view;
    //생성자에서 데이터 리스트 객체를 전달
    public RecyclerView_SimpleTextAdapter(ArrayList<MySpon> list, View view) {
        mDataList = list;
        this.view = view;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView spon_institution;
        TextView spon_donation;
        TextView spon_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 개체에 대한 참조
            spon_institution = (TextView) itemView.findViewById(R.id.my_spon_items_institution);
            spon_donation = (TextView) itemView.findViewById(R.id.my_spon_items_donation);
            spon_date = (TextView) itemView.findViewById(R.id.my_spon_items_date);
        }
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view  = inflater.inflate(R.layout.my_sponlist_fragment_recyclerview_contents, parent, false );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        String institution = mDataList.get(position).getInstitution();
        int donation = mDataList.get(position).getDonation();
        String date = mDataList.get(position).getDate();
        viewHolder.spon_institution.setText(institution);
        viewHolder.spon_donation.setText(Integer.toString(donation));
        viewHolder.spon_date.setText(date);
    }


    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return mDataList.size();
    }


}
