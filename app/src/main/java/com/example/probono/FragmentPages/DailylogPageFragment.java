package com.example.probono.FragmentPages;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.probono.DAO.JsonParsing.DialDateJsonParsing;
import com.example.probono.DAO.JsonParsing.DialogJsonParsing;
import com.example.probono.DAO.JsonParsing.JsonParser;
import com.example.probono.DAO.JsonParsing.SuccessMessageParsing;
import com.example.probono.DAO.JsonParsing.VisitDateJsonParsing;
import com.example.probono.DAO.NetworkTask.JsonNetworkTask;
import com.example.probono.DailylogPageFragment.CustomDialog;
import com.example.probono.DailylogPageFragment.DialogData;
import com.example.probono.DailylogPageFragment.decorator.DayListDecorator;
import com.example.probono.DailylogPageFragment.decorator.OneDayDecorator;
import com.example.probono.DailylogPageFragment.decorator.SaturdayDecorator;
import com.example.probono.DailylogPageFragment.decorator.SundayDecorator;
import com.example.probono.R;
import com.example.probono.staticValue.Sv;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DailylogPageFragment extends Fragment {
    private View view;
    private MaterialCalendarView calendar = null;
    private CustomDialog customDialog;
    ArrayList<Date> dayList;
    private String userid;
    //getContext().getResources().getString(R.string.server_ip)+
    private String url = Sv.dailylogDate;
    DayListDecorator dayListDecorator;
    TextView dialogTitle ;
    TextView dialogContent ;
    TextView dialogNo;
    Button dialogRepairButton;

    ArrayList<Date> visitDayList;

    public DailylogPageFragment(String userid){
        this.userid = userid;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calendar_layout, container, false);
        dialogTitle = ((TextView)view.findViewById(R.id.prevDialTitle));
        dialogContent = ((TextView)view.findViewById(R.id.prevDialContent));
        dialogNo = (TextView)view.findViewById(R.id.prevDialNo);
        this.calendar = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        this.dayList = new ArrayList<>();

        calendar.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2019, 0, 1))
                .setMaximumDate(CalendarDay.from(2030,11,31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        calendar.addDecorators(
                new SundayDecorator(),
                new OneDayDecorator(),
                new SaturdayDecorator()
        );

        // 달이 변경될 때 수행할 리스너
        // CalendarDay는 변경된 달의 첫 번째 날짜
        calendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                String selectedDay = date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDay();
                JSONObject param = new JSONObject();
                try {
                    param.put("visitDate", selectedDay);
                }catch (JSONException e){

                }
                new GetVisitDay(Sv.visitDay, param, new VisitDateJsonParsing()).execute();

            }
        });


        // 수정 버튼을 클릭하였을 때 수행할 리스너
        dialogRepairButton = (Button)view.findViewById(R.id.dialogRepairButton);
        dialogRepairButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View item) {
                CalendarDay date = calendar.getSelectedDate();
                String selectedDay = new SimpleDateFormat("yyyy-MM-dd").format(date.getDate());


                if(date != null){

                    customDialog = new CustomDialog(view.getContext(),new PositiveListener(), new NegativeListener());
                    customDialog.show();

                    TextView dialDateText = customDialog.findViewById(R.id.dialogDate);
                    EditText dialTitle = customDialog.findViewById(R.id.dialogSubject);
                    EditText dialContent= customDialog.findViewById(R.id.dialogEdit);
                    TextView dialNumber = customDialog.findViewById(R.id.dialogNumber);

                    dialDateText.setText(selectedDay);
                    dialTitle.setText(dialogTitle.getText());
                    dialContent.setText(dialogContent.getText());
                    dialNumber.setText(dialogNo.getText().toString());


                }
            }
        });

        // 캘린더의 날짜를 선택하였을 때 수행할 리스너
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView calendarView, @NonNull CalendarDay date, boolean selected) {

                String selectedDay = new SimpleDateFormat("yyyy-MM-dd").format(date.getDate());
                TextView dialogTitle  = ((TextView)view.findViewById(R.id.prevDialTitle));
                TextView dialogContent = ((TextView)view.findViewById(R.id.prevDialContent));


                // 방문 후원을 진행하지 않은 날짜를 선택할 경우 추가 불가
                if(!visitDayList.contains(date.getDate())){
                    calendarView.setDateSelected(date, false);
                    Toast.makeText(getContext(), "방문 날짜를 확인해 주세요 !", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (dayList.contains(date.getDate())) {
                        //  선택된 날짜에 등록된 일지 정보가 있을 경우
                        JSONObject param = new JSONObject();
                        try {
                            param.put("date", selectedDay);
                        }catch(JSONException e){

                        }

                        new showContent(Sv.dailylogDetail, param, new DialogJsonParsing()).execute();

                    } else {
                        dialogTitle.setText("");
                        dialogContent.setText("");

                        customDialog = new CustomDialog(getContext(), new PositiveListener(), new NegativeListener());
                        customDialog.show();

                        TextView dialDateText = customDialog.findViewById(R.id.dialogDate);
                        dialDateText.setText(selectedDay);
                    }
                }


            }
        });


        String nowDay =  new SimpleDateFormat("yyyy-MM").format(new Date());


        // 초기화작업에서 수행할 네트워크 작업
       JSONObject param = new JSONObject();
       try {
           param.put("visitDate", nowDay+"-01");
       }catch (JSONException e){

       }
       new GetVisitDay(Sv.visitDay, param, new VisitDateJsonParsing()).execute();
       new showCalendar(Sv.dailylogDate, null, new DialDateJsonParsing()).execute();

        return view;
    }


    class GetVisitDay extends JsonNetworkTask<ArrayList<Date>>{

        public GetVisitDay(String url, JSONObject param, JsonParser<ArrayList<Date>> parser) {
            super(url, param, parser);
        }


        @Override
        public void postTask(ArrayList<Date> result) {

            if(!result.isEmpty()){
                visitDayList = result;

            }else{

            }


        }
    }


    class showCalendar extends JsonNetworkTask<ArrayList<Date>>{

        public showCalendar(String url, JSONObject param, JsonParser<ArrayList<Date>> parser) {
            super(url, param, parser);
        }


        @Override
        public void postTask(ArrayList<Date> result) {

                if(result !=null){
                dayList = result;


                // 이전에 존재하던 데코레이터 삭제
                if(dayListDecorator!=null)
                    calendar.removeDecorator(dayListDecorator);

                // 데코레이터 생성  및 새로 등록
                dayListDecorator = new DayListDecorator(getContext(), result);

                calendar.addDecorators(
                        dayListDecorator
                 );
            }


        }
    }

    class showContent extends JsonNetworkTask<DialogData>{


        public showContent(String url, JSONObject param, JsonParser<DialogData> parser) {
            super(url, param, parser);
        }

        @Override
        public void postTask(DialogData dialogData) {
            if(dialogData==null) return ;
            dialogNo.setText(dialogData.getdNo()+"");
            dialogTitle.setText(dialogData.getTitle());
            dialogContent.setText(dialogData.getContent());
        }
    }

    class saveContent extends  JsonNetworkTask<String>{

        public saveContent(String url, JSONObject param, JsonParser<String> parser) {
            super(url, param, parser);
        }

        @Override
        public void postTask(String result) {
            if(result!=null && result.equals("SUCC")){
                Toast.makeText(getContext(), "일지 수정 완료", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(getContext(), "일지 수정 실패", Toast.LENGTH_LONG).show();

            }
        }
    }

    class PositiveListener implements View.OnClickListener{

        @Override
        public void onClick(View item) {
            TextView date = customDialog.findViewById(R.id.dialogDate);
            EditText title = customDialog.findViewById(R.id.dialogSubject);
            EditText content = customDialog.findViewById(R.id.dialogEdit);
            DialogData saveData = new DialogData(
                    userid,
                    date.getText().toString(),
                    title.getText().toString(),
                    content.getText().toString()
            );


            calendar.removeDecorator(dayListDecorator);
            dayList.add(calendar.getSelectedDate().getDate());


            // 네트워크로 저장할 데이터 전송
            JSONObject cv = new JSONObject();
            try {
                cv.put("seniorName", saveData.getTitle());
                cv.put("date", saveData.getDate());
                cv.put("content", saveData.getContent());

            }catch(JSONException e){

            }
            new saveContent( Sv.dailylogInsert, cv, new SuccessMessageParsing()).execute();

            calendar.addDecorator(new DayListDecorator(view.getContext(), dayList));
            TextView dialogTitle  = ((TextView)view.findViewById(R.id.prevDialTitle));
            TextView dialogContent = ((TextView)view.findViewById(R.id.prevDialContent));

            dialogTitle.setText(title.getText());
            dialogContent.setText(content.getText());


            customDialog.dismiss();
        }
    }

    class NegativeListener implements View.OnClickListener{
        @Override
        public void onClick(View item) {
            customDialog.dismiss();
        }
    }

}
