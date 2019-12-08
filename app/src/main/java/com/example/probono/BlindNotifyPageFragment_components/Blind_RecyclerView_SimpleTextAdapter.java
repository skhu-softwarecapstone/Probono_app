package com.example.probono.BlindNotifyPageFragment_components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.probono.DAO.JsonParsing.JsonParser;
import com.example.probono.DAO.JsonParsing.SuccessMessageParsing;
import com.example.probono.DAO.NetworkTask.JsonNetworkTask;
import com.example.probono.DTO.Blind;
import com.example.probono.DTO.Notice;
import com.example.probono.MainActivity;
import com.example.probono.R;
import com.example.probono.staticValue.Sv;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Blind_RecyclerView_SimpleTextAdapter extends RecyclerView.Adapter<Blind_RecyclerView_SimpleTextAdapter.ViewHolder> {
    private ArrayList<Blind> mDataList = null;
    private MainActivity activity = null;
    View view;

    private Context context;

    //생성자에서 데이터 리스트 객체를 전달
    public Blind_RecyclerView_SimpleTextAdapter(ArrayList<Blind> list, View view, Context context) {
        mDataList = list;
        this.view = view;
        this.context =context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView noti_id;
        TextView noti_content;

        TextView blind_title;
        TextView blind_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            blind_title = (TextView) itemView.findViewById(R.id.blind_items_title);
            blind_date = (TextView) itemView.findViewById(R.id.blind_items_date);


        }
    }

    // 아이템 뷰를 위한 뷰홀더 객체를 생성하여 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view  = inflater.inflate(R.layout.blindlist_page_fragment_recyclerview_contents, parent, false );

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        String title = mDataList.get(position).getName();
        String content = mDataList.get(position).getContent();
        String date = mDataList.get(position).getDate();

        viewHolder.blind_title.setText(title);
        viewHolder.blind_date.setText(date);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View item) {

                BlindContentDialog dialog = new BlindContentDialog(context, false);
                dialog.show();


                TextView blindTitle, blindContent, blindDate, blindNo;
                blindTitle = dialog.findViewById(R.id.blindSubject);
                blindContent = dialog.findViewById(R.id.blindEdit);
                blindDate = dialog.findViewById(R.id.blindDate);
                blindNo = dialog.findViewById(R.id.blindNo);

                ViewGroup.LayoutParams params = dialog.getWindow().getAttributes();
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);



                blindTitle.setText(mDataList.get(position).getName());
                blindContent.setText(mDataList.get(position).getContent());
                blindDate.setText(mDataList.get(position).getDate());
                blindNo.setText(mDataList.get(position).getbNo()+"");

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
