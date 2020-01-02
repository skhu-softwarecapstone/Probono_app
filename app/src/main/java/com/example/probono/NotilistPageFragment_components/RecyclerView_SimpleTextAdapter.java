package com.example.probono.NotilistPageFragment_components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DTO.Notice;
import com.example.probono.MainActivity;
import com.example.probono.DTO.Movie;
import com.example.probono.R;

import java.util.ArrayList;
import java.util.Date;

public class RecyclerView_SimpleTextAdapter extends RecyclerView.Adapter<RecyclerView_SimpleTextAdapter.ViewHolder> {
    private ArrayList<Notice> mDataList = null;
    private MainActivity activity = null;
    View view;
    NotiContentDialog dialog;
    //생성자에서 데이터 리스트 객체를 전달
    public RecyclerView_SimpleTextAdapter(ArrayList<Notice> list, View view) {
        mDataList = list;
        this.view = view;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView noti_id;
        TextView noti_content;

        TextView noti_title;
        TextView noti_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 개체에 대한 참조
            //noti_id = (TextView) itemView.findViewById(R.id.noti_items_id);
            noti_title = (TextView) itemView.findViewById(R.id.noti_items_title);
            //noti_content = (TextView) itemView.findViewById(R.id.noti_items_content);
            noti_date = (TextView) itemView.findViewById(R.id.noti_items_date);


        }
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        View view  = inflater.inflate(R.layout.notilist_page_fragment_recyclerview_contents, parent, false );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        //int id = mDataList.get(position).getId();
        //String content = mDataList.get(position).getContent();
        //viewHolder.noti_id.setText(Integer.toString(id));
        //viewHolder.noti_title.setText(content);

        String title = mDataList.get(position).getTitle();
        String date = mDataList.get(position).showDate();

        viewHolder.noti_title.setText(title);
        viewHolder.noti_date.setText(date);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View item) {
                dialog = new NotiContentDialog(view.getContext(), mDataList.get(position));
                ViewGroup.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

                dialog.show();


            }
        });
    }


    //전체 데이터 갯수 리턴
    @Override
    public int getItemCount() {
        if(mDataList!=null)
            return mDataList.size();
        return 0;
    }


}
