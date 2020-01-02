package com.example.probono.SponPageFragment_components;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.probono.DAO.NetworkTask.AddMysponNetworkTaskHandler;
import com.example.probono.DTO.Notice;
import com.example.probono.DTO.Spon;
import com.example.probono.R;


public class SponContentDialog extends Dialog {

    View view;
    TextView title;
    TextView content;
    TextView address;
    Button button;
    Button cancelButton;
    Spon data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.spon_content_dialog);

        title = findViewById(R.id.spon_dialog_title);
        content = findViewById(R.id.spon_dialog_content);
        address = findViewById(R.id.spon_dialog_address);
        button = findViewById(R.id.spon_dialog_check_button);
        cancelButton = findViewById(R.id.spon_dialog_cancel_button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                String url = "http://58.150.133.213:3000/process/add_mysponlist?";
                String id = "test001";
                String institution = data.getVolunteer_institution();
                int inputDonation = Integer.parseInt(String.valueOf(donation.getText()));

                String sendUrl = url + "id="+id + "&institution=" + institution +"&donation=" + inputDonation;
                System.out.println(sendUrl);
                AddMysponNetworkTaskHandler addMysponNetworkTaskHandler = new AddMysponNetworkTaskHandler(sendUrl, view);
                addMysponNetworkTaskHandler.execute();
                dismiss();
                */

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        title.setText(data.getTitle());
        content.setText(data.getContent());
        address.setText(data.getAddress());

    }

    public SponContentDialog(Context context) {
        super(context);
    }

    public SponContentDialog(Context context, Spon data, View view) {
        super(context);
        this.view = view;
        this.data = data;
    }
}
