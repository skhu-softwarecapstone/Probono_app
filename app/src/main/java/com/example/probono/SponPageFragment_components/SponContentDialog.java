package com.example.probono.SponPageFragment_components;

import android.app.Dialog;
import android.content.Context;
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
    TextView institution;
    TextView title;
    TextView content;
    TextView date;
    EditText donation;
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

        setContentView(R.layout.spon_content_dialog);

        title = findViewById(R.id.spon_dialog_title);
        content = findViewById(R.id.spon_dialog_content);
        date = findViewById(R.id.spon_dialog_date);
        button = findViewById(R.id.spon_dialog_check_button);
        donation = findViewById(R.id.spon_dialog_donation);
        cancelButton = findViewById(R.id.spon_dialog_cancel_button);
        institution = findViewById(R.id.spon_dialog_institution);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://58.150.133.213:3000/process/add_mysponlist?";
                String id = "test001";
                String institution = data.getVolunteer_institution();
                int inputDonation = Integer.parseInt(String.valueOf(donation.getText()));

                String sendUrl = url + "id="+id + "&institution=" + institution +"&donation=" + inputDonation;
                System.out.println(sendUrl);
                AddMysponNetworkTaskHandler addMysponNetworkTaskHandler = new AddMysponNetworkTaskHandler(sendUrl, view);
                addMysponNetworkTaskHandler.execute();
                dismiss();
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
        date.setText(data.showDate());
        institution.setText(data.getVolunteer_institution());

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
