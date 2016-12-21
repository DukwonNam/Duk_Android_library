package com.duk.lab.android.calendar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.duk.lab.android.R;

import java.util.Calendar;

/**
 * Created by Duk on 2016-12-19.
 */

public class CalendarFragment extends Fragment {

    private FrameLayout mCalendarContainer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.calendar_main, container, false);
        mCalendarContainer = (FrameLayout)view.findViewById(R.id.calendarContainer);
        mCalendarContainer.removeAllViews();
        final CalendarView calendarView = new CalendarView(getActivity());
        calendarView.setMonth(Calendar.getInstance(), false);
//        mCalendarContainer.addView(calendarView);
        mCalendarContainer.setVisibility(View.GONE);

        return view;
    }
}
