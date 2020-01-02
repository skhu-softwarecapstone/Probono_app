package com.example.probono.DailylogPageFragment.decorator;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.probono.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DayListDecorator implements DayViewDecorator {
    ArrayList<Date> dayList = null;
    Calendar calendar = Calendar.getInstance();
    Context context = null;

    public DayListDecorator(Context context,ArrayList<Date> dayList){
        this.dayList = dayList;
        this.context = context;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
        Date date = calendar.getTime();
        return dayList.contains(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(ContextCompat.getDrawable(this.context, R.drawable.point_drawable2));

    }
}
