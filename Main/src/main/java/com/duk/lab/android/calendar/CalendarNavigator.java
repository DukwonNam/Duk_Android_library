package com.duk.lab.android.calendar;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.duk.lab.android.R;

/**
 * Created by Duk on 2016-12-19.
 */

public class CalendarNavigator extends FrameLayout {
    private ViewPager mCalendarPager;

    public CalendarNavigator(Context context) {
        super(context);
        initViews();
    }

    public CalendarNavigator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public CalendarNavigator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        View.inflate(getContext(), R.layout.calendar_navigator, this);
        mCalendarPager = (ViewPager)findViewById(R.id.calendarPager);
    }
}
