package com.example.dedra.bukukerjamandor;


import java.util.GregorianCalendar;
import java.util.Locale;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.dedra.bukukerjamandor.R.id.calendarView;

/**
 * Created by Dedra on 03/08/2017.
 */

public class calendar extends Fragment {

    private static final String TAG = "calendar";

    private CalendarView mCalendarView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.calendar, container, false);

        mCalendarView = (CalendarView) view.findViewById(R.id.calendarView); // get the reference of CalendarView

        mCalendarView.getDate();

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth){
                String date = year + "/" + month + "/" + dayOfMonth;
                Log.d(TAG, "onSelectedDayChange: date: " + date);
            }
        });

        mCalendarView.setFirstDayOfWeek(2); // set Monday as the first day of the week

        /*
        View view = inflater.inflate(R.layout.calendar,null);

        Locale.setDefault(Locale.US);
        month = (GregorianCalendar)
        calendarView = (CalendarView) findViewbyId(R.id.calendarView);
*/
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Lihat Kalendar");

    }

}
