package com.example.probono.BlindNotifyPageFragment_components;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.probono.DAO.JsonParsing.JsonParser;
import com.example.probono.DAO.JsonParsing.SuccessMessageParsing;
import com.example.probono.DAO.NetworkTask.JsonNetworkTask;
import com.example.probono.DTO.Notice;
import com.example.probono.R;
import com.example.probono.staticValue.Sv;

import org.json.JSONException;
import org.json.JSONObject;


public class BlindContentDialog extends Dialog {


    Context context;
    Boolean isInsert=false;
    String url= Sv.blindRevise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);


        setContentView(R.layout.blind_dialog);
        //button

        findViewById(R.id.blindSignBtn).setOnClickListener(v->{
            TextView blindTitle, blindContent, blindDate, blindNo;
            blindTitle = findViewById(R.id.blindSubject);
            blindContent = findViewById(R.id.blindEdit);
            blindDate = findViewById(R.id.blindDate);
            blindNo  = findViewById(R.id.blindNo);


            JSONObject jsonObject = new JSONObject();
            try {
                if(!isInsert)
                    jsonObject.put("bNo", Integer.parseInt(blindNo.getText().toString()));
                jsonObject.put("name", blindTitle.getText().toString());
                jsonObject.put("content", blindContent.getText().toString() );
            }catch (JSONException e){
            }

            if(isInsert) url = Sv.blindInsert;

            new Task(url, jsonObject, new SuccessMessageParsing()).execute();
            dismiss();
        });
        findViewById(R.id.blindCancelBtn).setOnClickListener(v->{ dismiss(); });
    }

    public BlindContentDialog(Context context, Boolean isInsert) {
        super(context);
        this.isInsert = isInsert;
        this.context = context;
    }

    class Task extends JsonNetworkTask<String> {

        public Task(String url, JSONObject param, JsonParser<String> parser) {
            super(url, param, parser);
        }

        @Override
        public void postTask(String result) {

            if(result!=null&&result.equals("SUCC")){

                Toast.makeText(context, "등록 성공", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(context, "등록 실패", Toast.LENGTH_LONG).show();

            }
        }
    }
}
