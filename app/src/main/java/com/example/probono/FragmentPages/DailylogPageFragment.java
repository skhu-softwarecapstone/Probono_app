package com.example.probono.FragmentPages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.probono.DAO.NetworkTask.DialNetworkTaskHandler;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DailylogPageFragment extends Fragment {
    View view;
    String userid;

    public DailylogPageFragment(String userid){
        this.userid = userid;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.calendar_layout, container, false);

        new DialNetworkTaskHandler(view, userid).execute();

        return view;
    }

}
