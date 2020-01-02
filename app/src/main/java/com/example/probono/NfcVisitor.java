package com.example.probono;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.probono.DAO.JsonParsing.JsonParser;
import com.example.probono.DAO.JsonParsing.SuccessMessageParsing;
import com.example.probono.DAO.NetworkTask.JsonNetworkTask;
import com.example.probono.staticValue.Sv;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class NfcVisitor extends AppCompatActivity {
    TextView printNfcContent, isNfcOn;
    Button goHomebtn;
    ImageView nfc_phone, nfc_card;
    NfcDialog dial;
    String userId;


    public static final String MIME_TEXT_PLAIN = "text/plain";
    private NfcAdapter nfcAdapter;

    AppCompatActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_visitor);
        this.userId = getIntent().getStringExtra("userId");
        activity = this;

        goHomebtn = findViewById(R.id.goHomeBtn);
        printNfcContent = findViewById(R.id.printNfcContent);
        nfc_card = findViewById(R.id.card);
        nfc_phone = findViewById(R.id.nfc_phone);
        isNfcOn = findViewById(R.id.is_nfc_on);

        if (!isNfcSupported()) {
            Toast.makeText(this, "NFC를 사용할 수 없는 기기 입니다", Toast.LENGTH_LONG).show();
            finish();
        }
        if (!nfcAdapter.isEnabled()) {
            Toast.makeText(this, "NFC 기능이 꺼져 있습니다", Toast.LENGTH_LONG).show();
            isNfcOn.setText("NFC 설정 후 재시작 해주세요");

            dial = new NfcDialog(this, v->{
                Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
                startActivity(intent);
                dial.dismiss();
            });

            dial.show();

        }
        else{
            Toast.makeText(this, "NFC  활성화", Toast.LENGTH_SHORT).show();
        }


        goHomebtn.setOnClickListener(v->{
            disableForegroundDispatch(this, this.nfcAdapter);
            finish();
        });

        nfc_card.startAnimation(AnimationUtils.loadAnimation(this, R.anim.nfc_action));



    }
    class NfcDialog extends Dialog{
        Button okbtn, nobtn;
        View.OnClickListener listener;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.custom_dialog_nfc);
            okbtn = findViewById(R.id.go_nfc_setting);

            okbtn.setOnClickListener(listener);
            nobtn = findViewById(R.id.no_nfc_setting);
            nobtn.setOnClickListener(v->{
                dismiss();
            });
        }

        public NfcDialog(@NonNull Context context, View.OnClickListener listener) {
            super(context);

            this.listener = listener;
        }
    }

    private boolean isNfcSupported() {
        this.nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        return this.nfcAdapter != null;
    }

    // NFC 가 인식되면 인텐트가 생성되어 전달됨
    @Override
    protected void onNewIntent(Intent intent) {
        receiveMessageFromDevice(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        enableForegroundDispatch(this, this.nfcAdapter);
        receiveMessageFromDevice(getIntent());
    }



    @Override
    protected void onPause() {
        super.onPause();
        disableForegroundDispatch(this, this.nfcAdapter);
    }


    // 인텐트 전달시 실행될 메서드
    // 클라이언트에서 시간 조작이 가능하므로
    // 시간 정보는 서버에서 등록하자 !
    private void receiveMessageFromDevice(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            NdefMessage inNdefMessage = (NdefMessage) parcelables[0];
            NdefRecord[] inNdefRecords = inNdefMessage.getRecords();
            NdefRecord ndefRecord_0 = inNdefRecords[0];

            String inMessage = new String(ndefRecord_0.getPayload());
            //this.printNfcContent.setText(inMessage);

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", inMessage);
            }catch (JSONException e){

            }
            new Task(Sv.Nfc, jsonObject, new SuccessMessageParsing()).execute();
            // 네트워크 작업 수행


        }
    }


    public void enableForegroundDispatch(AppCompatActivity activity, NfcAdapter adapter) {



        final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        //
        final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};

        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filters[0].addDataType(MIME_TEXT_PLAIN);
        } catch (IntentFilter.MalformedMimeTypeException ex) {
            throw new RuntimeException("Check your MIME type");
        }

        adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
    }

    public void disableForegroundDispatch(final AppCompatActivity activity, NfcAdapter adapter) {
        adapter.disableForegroundDispatch(activity);
    }


    class Task extends JsonNetworkTask<String>{

        public Task(String url, JSONObject param, JsonParser<String> parser) {
            super(url, param, parser);
        }

        @Override
        public void postTask(String result) {
            if(result!=null&&result.equals("SUCC")){

                Toast.makeText(getApplicationContext(), "방문 확인 !", Toast.LENGTH_LONG).show();
                disableForegroundDispatch(activity, nfcAdapter);
                isNfcOn.setText("방문 확인되었습니다");
            }else{

                Toast.makeText(getApplicationContext(), "등록 실패 ! 다시 시도해 주세요", Toast.LENGTH_LONG).show();

            }
        }
    }
}
