package com.example.probono.NotilistPageFragment_components;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.probono.DTO.Notice;
import com.example.probono.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class NotiContentDialog extends Dialog {

    TextView title;
    TextView content;
    TextView date;
    Button button;
    Notice data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;

        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.noti_content_dialog);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        title = findViewById(R.id.noti_dialog_title);
        content = findViewById(R.id.noti_dialog_content);
        date = findViewById(R.id.noti_dialog_date);
        button = findViewById(R.id.noti_dialog_check_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        title.setText(data.getTitle());
        content.setText(data.getContent());
        date.setText(data.showDate());


    }

    public NotiContentDialog(Context context) {
        super(context);
    }

    public NotiContentDialog(Context context, Notice data) {
        super(context);

        this.data = data;
    }
}
