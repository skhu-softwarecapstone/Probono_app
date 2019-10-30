package com.example.probono.SponPageFragment_components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DTO.Spon;
import com.example.probono.MainActivity;
import com.example.probono.R;

import java.util.ArrayList;
import java.util.Date;

public class RecyclerView_SimpleTextAdapter extends RecyclerView.Adapter<RecyclerView_SimpleTextAdapter.ViewHolder> {
    private ArrayList<Spon> mDataList = null;
    private MainActivity activity = null;
    private  View view;
    private SponContentDialog dialog;
    //생성자에서 데이터 리스트 객체를 전달
    public RecyclerView_SimpleTextAdapter(ArrayList<Spon> list, View view) {
        mDataList = list;
        this.view = view;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView spon_id;
        TextView spon_title;
        TextView spon_date;
        TextView spon_volunteerInstitution;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 개체에 대한 참조
            spon_id = (TextView) itemView.findViewById(R.id.spon_items_id);
            spon_title = (TextView) itemView.findViewById(R.id.spon_items_title);
            spon_date = (TextView) itemView.findViewById(R.id.spon_items_date);
            spon_volunteerInstitution = (TextView) itemView.findViewById(R.id.spon_items_volunteerInstitution);


        }
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view  = inflater.inflate(R.layout.spon_page_fragment_recyclerview_contents, parent, false );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        String title = mDataList.get(position).getTitle();
        int id = mDataList.get(position).getId();
        String date = mDataList.get(position).showDate();
        String content = mDataList.get(position).getContent();
        String volunteerInstitution = mDataList.get(position).getVolunteer_institution();
        viewHolder.spon_id.setText(Integer.toString(id));
        viewHolder.spon_title.setText(title);
        viewHolder.spon_volunteerInstitution.setText(volunteerInstitution);
        viewHolder.spon_date.setText(date);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View item) {
                dialog=new SponContentDialog(view.getContext(), mDataList.get(position), view);
                dialog.show();
            }
        });

    }


    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        return mDataList.size();
    }


}
