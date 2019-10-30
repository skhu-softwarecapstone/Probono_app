package com.example.probono.DAO.NetworkTask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.probono.DAO.JsonParsing.DialDateJsonParsing;
import com.example.probono.DAO.RequestHttpURLConnection;
import com.example.probono.DailylogPageFragment.CustomDialog;
import com.example.probono.DailylogPageFragment.DialogData;
import com.example.probono.DailylogPageFragment.HttpClient;
import com.example.probono.DailylogPageFragment.decorator.DayListDecorator;
import com.example.probono.DailylogPageFragment.decorator.OneDayDecorator;
import com.example.probono.DailylogPageFragment.decorator.SaturdayDecorator;
import com.example.probono.DailylogPageFragment.decorator.SundayDecorator;
import com.example.probono.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DialNetworkTaskHandler extends AsyncTask<Void, Void, String> {
    private View view;
    private MaterialCalendarView calendar = null;
    private CustomDialog customDialog;
    ArrayList<DialogData> dialogList;
    ArrayList<Date> dayList;
    private String userid;
    private String url = "http://58.150.133.213:3000/process/select_dailylog_dates";

    DayListDecorator dayListDecorator;


    public DialNetworkTaskHandler(View view, String userid){
        this.view = view;
        this.userid = userid;
        this.calendar = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        calendar.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(2019, 0, 1))
                .setMaximumDate(CalendarDay.from(2030,11,31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();


        dayList = new ArrayList<>();


    }

    @Override
    protected void onPostExecute(String dialogData) {
        super.onPostExecute(dialogData);

        ArrayList<String> dialdate = new ArrayList<>();
        new DialDateJsonParsing(dialogData).jsonParsing(dayList);


        dayListDecorator = new DayListDecorator(view.getContext(), dayList);
        calendar.addDecorators(
                new SundayDecorator(),
                new OneDayDecorator(),
                new SaturdayDecorator(),
                dayListDecorator
        );

        Button dialogRepairButton = (Button)view.findViewById(R.id.dialogRepairButton);
        dialogRepairButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View item) {
                CalendarDay date = calendar.getSelectedDate();

                if(date != null){
                    String selectedDay = date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDay();
                    customDialog = new CustomDialog(view.getContext(),new PositiveListener(), new NegativeListener());

                    customDialog.show();
                    TextView dialDateText = customDialog.findViewById(R.id.dialogDate);
                    EditText dialTitle = customDialog.findViewById(R.id.dialogSubject);
                    EditText dialContent= customDialog.findViewById(R.id.dialogEdit);

                    dialDateText.setText(selectedDay);
                    dialTitle.setText(((TextView)view.findViewById(R.id.prevDialTitle)).getText());
                    dialContent.setText(((TextView)view.findViewById(R.id.prevDialContent)).getText());



                }
            }
        });

        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView calendarView, @NonNull CalendarDay date, boolean selected) {

                String selectedDay = date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDay();
                TextView dialogTitle  = ((TextView)view.findViewById(R.id.prevDialTitle));
                TextView dialogContent = ((TextView)view.findViewById(R.id.prevDialContent));

                if(dayList.contains(date.getDate())){
                    //  이 부분에 데이터 가져오는거 만들어야됨
                    new DialContentNetworkTaskHandler(userid, selectedDay, view).execute();
                    /*
                    DialogData dialogData =dialogList.get(dayList.indexOf(date.getDate()));
                    dialogTitle.setText(dialogData.getTitle());
                    dialogContent.setText(dialogData.getContent());

                     */

                }else{
                    dialogTitle.setText("");
                    dialogContent.setText("");

                    customDialog = new CustomDialog(view.getContext(),new PositiveListener(), new NegativeListener());

                    customDialog.show();
                    TextView dialDateText = customDialog.findViewById(R.id.dialogDate);
                    dialDateText.setText(selectedDay);
                }


            }
        });



    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        result = requestHttpURLConnection.request(url+"?id="+userid, null); //해당 URL로부터 결과물을 얻어온다.
        System.out.println(url);

        return result;
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


            new DialContentSaveNetworkTaskHandler(saveData).execute();

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
