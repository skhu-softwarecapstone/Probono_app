package com.example.probono.DailylogPageFragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import com.example.probono.R;


public class CustomDialog extends Dialog {
    private View.OnClickListener positiveListener;
    private View.OnClickListener negativeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.custom_dialog);
        //button

        findViewById(R.id.dialogSignBtn).setOnClickListener(positiveListener);
        findViewById(R.id.dialogCancelBtn).setOnClickListener(negativeListener);
    }

    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, View.OnClickListener positive, View.OnClickListener negative) {
        super(context);
        this.positiveListener = positive;
        this.negativeListener = negative;
    }
}
